package com.Group8.ToDo.service.service;

import com.Group8.ToDo.service.config.Producer;
import com.Group8.ToDo.service.exceeptin.TaskAlreadyExistException;
import com.Group8.ToDo.service.exceeptin.TaskNotFoundException;
import com.Group8.ToDo.service.exceeptin.UserAlreadyRegistered;
import com.Group8.ToDo.service.exceeptin.UserNotFoundException;
import com.Group8.ToDo.service.model.Priority;
import com.Group8.ToDo.service.model.Task;
import com.Group8.ToDo.service.model.User;
import com.Group8.ToDo.service.proxy.UserArchiveProxy;
import com.Group8.ToDo.service.proxy.UserAuthProxy;
import com.Group8.ToDo.service.rabbitMQ.UserDTO;
import com.Group8.ToDo.service.repository.UserToDoServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class To_Do_ServiceImpl implements To_Do_Service {

    private EmailService emailService;
    private UserToDoServiceRepository repository;
    private UserAuthProxy authProxy;
    private UserArchiveProxy archieveProxy;

private Producer producer;
    private SequenceGenerator sequenceGenerator;

//    private JavaMailSender javaMailSender;

@Autowired
    public To_Do_ServiceImpl(Producer producer,EmailService emailService, UserToDoServiceRepository repository, UserAuthProxy authProxy, UserArchiveProxy archieveProxy, SequenceGenerator sequenceGenerator) {
        this.emailService = emailService;
        this.producer= producer;
        this.repository = repository;
        this.authProxy = authProxy;
        this.archieveProxy = archieveProxy;
        this.sequenceGenerator = sequenceGenerator;
    }

    @Override
    public User registerUser(User user) throws UserAlreadyRegistered {
        System.out.println("Now check");
        if (repository.findById(user.getUserEmailId()).isPresent()) {
            throw new UserAlreadyRegistered();
        }
        UserDTO userDTO=new UserDTO();
        userDTO.setUserEmailId(user.getUserEmailId());
        userDTO.setUserName(user.getUserName());
        userDTO.setUserPassword(user.getUserPassword());
        System.out.println("Now i need to check");
//        authProxy.saveuserdeatilstodatabase(user);
        System.out.println("Save to Auth");
//        archieveProxy.registerUser(user);
        producer.sendMessageToRabbitMq(userDTO);
        System.out.println("save Rabbit");
        emailService.registerUser(user);
        System.out.println("mail send");
        return repository.save(user);
    }

    @Override
    public User updateProfile(String userEmailId, User user) throws UserNotFoundException {
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObj = repository.findByUserEmailId(userEmailId);
        userObj.setUserName(user.getUserName());
        userObj.setUserPassword(user.getUserPassword());
        userObj.setDateOfBirth(user.getDateOfBirth());
        userObj.setUserGender(user.getUserGender());
        repository.save(userObj);
        emailService.updateProfile(user);
        System.out.println("User Profile Has Been Updated");
//        authProxy.updateUser(userEmailId,user);
        return userObj;
    }

    @Override
    public String getUserName(String userEmailId) throws UserNotFoundException {
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = repository.findById(userEmailId).get();
        return user.getUserName();
    }

    @Override
    public User getUserDetails(String userEmailId) throws UserNotFoundException {
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user=repository.findByUserEmailId(userEmailId);
      return repository.findByUserEmailId(userEmailId);
//        return user.getUserEmailId() + " " + user.getUserName() + " " + user.getUserGender() + " " + user.getDateOfBirth();

    }

    @Override
    public User forgotPassword(String userEmailId, String userName, User user) throws UserNotFoundException {
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
//        User user=repository.findByUserEmailId(userEmailId);
        return repository.findByUserEmailId(userEmailId);

    }

    @Override
    public User addTaskToUser(Task task, String userEmailId) throws UserNotFoundException, TaskAlreadyExistException {
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObject = repository.findByUserEmailId(userEmailId);
        System.out.println("insaid addtask in service" + userObject);
        task.setTaskId(sequenceGenerator.SequenceNumber(Task.SEQUENCE_NAME));
        List<Task> tasks;
        if (userObject.getUserTask() == null) {
            tasks = new ArrayList<>();
        } else {
            tasks = userObject.getUserTask();
            for (Task task1 : tasks) {
                if (task1.getTaskHeading().equalsIgnoreCase(task.getTaskHeading())) {
                    throw new TaskAlreadyExistException();
                }
            }
        }
        tasks.add(task);
        userObject.setUserTask(tasks);
        return repository.save(userObject);
    }

    @Override
    public User updateTask(Task task, String userEmailId) throws UserNotFoundException, TaskNotFoundException {
        System.out.println("I m in Service impl");
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User user = repository.findById(userEmailId).get();
        List<Task> tasks = user.getUserTask();
        Task task2 = null;
        for (Task t : tasks) {
            if (task.getTaskId() == t.getTaskId()) {
                task2 = t;
                System.out.println("Task matched");
                break;
            }
        }
        if (task2 == null) {
            throw new TaskNotFoundException();
        } else {
            user.getUserTask().remove(task2);
            tasks.add(task);
            user.setUserTask(tasks);
            repository.save(user);
        }
        return repository.save(user);
    }

    @Override
    public User CompleteTask(int taskId, String userEmailId) throws UserNotFoundException, TaskNotFoundException {
        System.out.println("Check to remove");
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObj=repository.findByUserEmailId(userEmailId);
        List<Task> userTask=userObj.getUserTask();
List<Task> arr=new ArrayList<>();
        if(userTask.isEmpty())
        {
            throw new TaskNotFoundException();
        }
        for (Task t:userTask) {
            if(t.getTaskId()== taskId)
            {
               t.setStatus(!t.isStatus());
            }
            arr.add(t);

userObj.setUserTask(arr);        }
        return repository.save(userObj);
    }

    @Override
    public User removeTaskToRecycle(Task task, String userEmailId, int taskId) throws UserNotFoundException, TaskNotFoundException {
        System.out.println("check for archive add ");
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObj=repository.findByUserEmailId(userEmailId);
        List<Task> userTask=userObj.getUserTask();
        System.out.println(userTask);
        List<Task> deleteTask;
        if(userTask==null)
        {
            throw new TaskNotFoundException();
        }
        for (Task t:userTask) {
            System.out.println(t.getTaskId());
            System.out.println("CHeck if Condition");
            System.out.println(t.getTaskId()== taskId);
            if(t.getTaskId()== taskId)
            {
                userTask.remove(t);
                System.out.println("removed by list");

                if (userObj.getDeletedTask() == null) {
                    deleteTask = new ArrayList<>();
                } else {
                   deleteTask = userObj.getDeletedTask();

                }
                deleteTask.add(t);
                userObj.setDeletedTask(deleteTask);
                System.out.println("added to recycle");
                return repository.save(userObj);
            }
        }
        return repository.save(userObj);
    }

    @Override
    public User shiftToArchieveList(Task task, String userEmailId,int taskId) throws UserNotFoundException, TaskAlreadyExistException, TaskNotFoundException {
        System.out.println("check for archive add ");
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObj=repository.findByUserEmailId(userEmailId);
        List<Task> userTask=userObj.getUserTask();
        System.out.println(userTask);
        List<Task> archieve;
        if(userTask==null)
        {
            throw new TaskNotFoundException();
        }
        for (Task t:userTask) {
            System.out.println(t.getTaskId());
            System.out.println("CHeck if Condition");
            System.out.println(t.getTaskId()== taskId);
            if(t.getTaskId()== taskId)
            {
                userTask.remove(t);
                System.out.println("removed by list");

                if (userObj.getArchieveTask() == null) {
                    archieve = new ArrayList<>();
                } else {
                    archieve = userObj.getArchieveTask();

                }
                archieve.add(t);
                userObj.setArchieveTask(archieve);
                System.out.println("aaded to archieve");
                return repository.save(userObj);
            }
        }
        return repository.save(userObj);
    }

    @Override
    public User ReShiftToUserTask(Task task, String userEmailId, int taskId) throws UserNotFoundException, TaskAlreadyExistException, TaskNotFoundException {
        System.out.println("check for archive add ");
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObj=repository.findByUserEmailId(userEmailId);
        List<Task> archieTask=userObj.getArchieveTask();

        List<Task> userTask;
        if(archieTask==null)
        {
            throw new TaskNotFoundException();
        }
        for (Task t:archieTask) {
            System.out.println(t.getTaskId());
            System.out.println("CHeck if Condition");
            System.out.println(t.getTaskId()== taskId);
            if(t.getTaskId()== taskId)
            {
                archieTask.remove(t);
                System.out.println("removed by list");

                if (userObj.getUserTask() == null) {
                    userTask = new ArrayList<>();
                } else {
                    userTask = userObj.getUserTask();

                }
                userTask.add(t);
                userObj.setUserTask(userTask);
                System.out.println("aaded to archieve");
                return repository.save(userObj);
            }
        }
        return repository.save(userObj);
    }

    @Override
    public User restoreTask(Task task, String userEmailId, int taskId) throws UserNotFoundException, TaskNotFoundException {
        System.out.println("check for archive add ");
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObj=repository.findByUserEmailId(userEmailId);
        List<Task> deletedTask=userObj.getDeletedTask();

        List<Task> userTask;
        if(deletedTask==null)
        {
            throw new TaskNotFoundException();
        }
        for (Task t:deletedTask) {
            System.out.println(t.getTaskId());
            System.out.println("CHeck if Condition");
            System.out.println(t.getTaskId()== taskId);
            if(t.getTaskId()== taskId)
            {
                deletedTask.remove(t);
                System.out.println("removed by list");

                if (userObj.getUserTask() == null) {
                    userTask = new ArrayList<>();
                } else {
                    userTask = userObj.getUserTask();

                }
                userTask.add(t);
                userObj.setUserTask(userTask);
                System.out.println("aaded to archieve");
                return repository.save(userObj);
            }
        }
        return repository.save(userObj);
    }

    @Override
    public List<Task> getAllArchieveTask(String userEmailId) throws UserNotFoundException {
        User user = repository.findByUserEmailId(userEmailId);
        System.out.println(user.getArchieveTask());
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        else {
            if(user.getArchieveTask()==null)
            {
                System.out.println("User Archieve list Is Empty");
            }
            return user.getArchieveTask();
        }
    }

    @Override
    public List<Task> getAllDeletedTask(String userEmailId) throws UserNotFoundException {
        User user = repository.findByUserEmailId(userEmailId);
        System.out.println(user.getArchieveTask());
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        else {
            if(user.getDeletedTask()==null)
            {
                System.out.println("User ReCycle Bin  Is Empty");
            }
            return user.getDeletedTask();
        }
    }

//    delete task

    @Override
    public User deleteArTask(int taskId, String userEmailId) throws UserNotFoundException {
        System.out.println("Delete ServiceImpl");
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User u = repository.findByUserEmailId(userEmailId);
        List<Task> list = u.getArchieveTask();
        System.out.println("user Find ");
        for (Task x : list) {
            if (taskId == x.getTaskId()) {
                System.out.println("Task find");
                list.remove(x);
                System.out.println("Task Removed");
                u.setArchieveTask(list);
                System.out.println("User task Collide");
               return repository.save(u);
            }
        }
        return  repository.save(u);
    }

    @Override
    public User deleteDlTask(int taskId, String userEmailId) throws UserNotFoundException {
        System.out.println("Delete ServiceImpl");
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User u = repository.findByUserEmailId(userEmailId);
        List<Task> list = u.getDeletedTask();
        System.out.println("user Find ");
        for (Task x : list) {
            if (taskId == x.getTaskId()) {
                System.out.println("Task find");
                list.remove(x);
                System.out.println("Task Removed");
                u.setDeletedTask(list);
                System.out.println("User task Collide");
                return repository.save(u);
            }
        }
        return  repository.save(u);
    }

    @Override
    public List<Task> getAllTask(String userEmailId) throws UserNotFoundException {
        User user = repository.findById(userEmailId).get();
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        else {
            if(user.getUserTask()==null)
            {
                System.out.println("User Task list Is Empty");
            }
            return user.getUserTask();
        }
    }

    @Override
    public List<Task> getPendingTask(String userEmailId) throws TaskNotFoundException {

        return repository.findById(userEmailId).get().getUserTask().stream().filter(t->t.isStatus()).collect(Collectors.toList());
    }

    @Override
    public List<Task> getCompletedTask(String userEmailId) throws TaskNotFoundException{

        return repository.findById(userEmailId).get().getUserTask().stream().filter(t->!t.isStatus()).collect(Collectors.toList());
    }


    @Override
    public List<Task> categorizeByStartDate(String userEmailId) throws UserNotFoundException {
        if (repository.existsById(userEmailId)) {
            return repository.findById(userEmailId).get().getUserTask()
                    .stream().sorted(Comparator.comparing(Task::getTaskStartDate)).collect(Collectors.toList());

        }
        throw new UserNotFoundException();
    }

    @Override
    public List<Task> categorizeByEndDate(String userEmailId) throws UserNotFoundException {
        if (repository.existsById(userEmailId)) {
            return repository.findById(userEmailId).get().getUserTask()
                    .stream().sorted(Comparator.comparing(Task::getTaskEndDate)).collect(Collectors.toList());
        }
        throw new UserNotFoundException();
    }

    @Override
    public List<Task> categorizeByHighPriority(String userEmailId) throws UserNotFoundException {
        User u=repository.findById(userEmailId).get();
        List<Task> list=u.getUserTask();
        List<Task> highPrioritylist=new ArrayList<>();
        for(Task x:list){
            if(x.getPriority()== Priority.HIGH){
                highPrioritylist.add(x);
                System.out.println(highPrioritylist);
            }
        }
        return highPrioritylist;
    }

    @Override
    public List<Task> categorizeByLowPriority(String userEmailId) throws UserNotFoundException {
        User u=repository.findById(userEmailId).get();
        List<Task> list=u.getUserTask();
        List<Task> highPrioritylist=new ArrayList<>();
        for(Task x:list){
            if(x.getPriority()== Priority.LOW){
                highPrioritylist.add(x);
                System.out.println(highPrioritylist);
            }
        }
        return highPrioritylist;
    }


    @Override
    public Task getTask(String userEmailId, int taskId) throws TaskNotFoundException, UserNotFoundException {
        if (repository.findById(userEmailId).isPresent()) {
            User user = repository.findById(userEmailId).get();
            List<Task> tasks = user.getUserTask();
            Task task2 = null;
            for (Task task1 : tasks) {
                if (task1.getTaskId() == taskId) {
                    task2 = task1;
                    break;
                }
            }
            if (task2 == null) {
                throw new TaskNotFoundException();
            }
            return task2;
        } else {
            throw new UserNotFoundException();
        }
    }



    Comparator<Task> dueDateComparator = ((task1, task2) -> {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        LocalDate dueDate1 = task1.getTaskEndDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        System.out.println("***********************************"+dueDate1);
        System.out.println(task1);
        System.out.println(task2);

        LocalDate dueDate2 = task2.getTaskEndDate().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        System.out.println("*****************************************"+dueDate2);
        if (dueDate1.isBefore(dueDate2)) {
            return -1;
        }
        if (dueDate1.isAfter(dueDate2)) {
            return 1;
        }
        return 0;
    });

    @Override
    public List<Task> getTasksWithNearDueDate(String userEmailId) throws UserNotFoundException {
        String localdate= String.valueOf(LocalDate.now());
        User u=repository.findByUserEmailId(userEmailId);
        List<Task> list=u.getUserTask();
        List<Task> enddaytask = new ArrayList<>();
        for(Task x:list){


            Date end=x.getTaskEndDate();
            Instant instant=end.toInstant();
            ZonedDateTime z=instant.atZone(ZoneId.systemDefault());
            System.out.println("end day==="+z.toLocalDate());

            Date start=x.getTaskStartDate();
            Instant instan=start.toInstant();
            ZonedDateTime z1=instan.atZone(ZoneId.systemDefault());
            System.out.println("start day==="+z1.toLocalDate());


            String endday= String.valueOf(z.toLocalDate());
            String startday= String.valueOf(z1.toLocalDate());

            System.out.println(localdate+"  ==  "+z.toLocalDate());
            if(localdate.equals(endday) == true){
                enddaytask.add(x);

            }
        }

        return enddaytask;
    }

    @Override
    public List<Task> getTodayTask (String userEmailId) throws UserNotFoundException {
        String localdate= String.valueOf(LocalDate.now());
        User u=repository.findByUserEmailId(userEmailId);
        List<Task> list=u.getUserTask();
        List<Task> enddaytask = new ArrayList<>();
        for(Task x:list){


            Date end=x.getTaskEndDate();
            Instant instant=end.toInstant();
            ZonedDateTime z=instant.atZone(ZoneId.systemDefault());
            System.out.println("end day==="+z.toLocalDate());

            Date start=x.getTaskStartDate();
            Instant instan=start.toInstant();
            ZonedDateTime z1=instan.atZone(ZoneId.systemDefault());
            System.out.println("start day==="+z1.toLocalDate());


            String endday= String.valueOf(z.toLocalDate());
            String startday= String.valueOf(z1.toLocalDate());

            System.out.println(localdate+"  ==  "+z.toLocalDate());
            if(localdate.equals(startday) == true){
                enddaytask.add(x);

            }

        }

        return enddaytask;
    }

    @Override
    public String feedbackForm(String userEmail, String message) {
        System.out.println("Give Feed Back");
       return emailService.feedBack(userEmail,message);
    }

    @Override
    public List<Task> getTasksWithOverDue(String userEmail) throws UserNotFoundException {
        DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Optional<User> user = repository.findById(userEmail);
        if (user.isEmpty()) {
            throw new UserNotFoundException();
        }
        List<Task> allTasks = user.get().getUserTask();
        List<Task> tasksOverDue = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.isStatus()) {
                continue;
            }
            LocalDate dueDate =task.getTaskEndDate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            if (dueDate.isBefore(LocalDate.now())) {
                tasksOverDue.add(task);
            }
        }
        return tasksOverDue.stream().sorted(dueDateComparator).collect(Collectors.toList());
    }
   }
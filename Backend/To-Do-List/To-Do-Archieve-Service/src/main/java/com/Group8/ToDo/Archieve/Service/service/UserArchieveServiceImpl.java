package com.Group8.ToDo.Archieve.Service.service;

import com.Group8.ToDo.Archieve.Service.exception.TaskAlreadyExistException;
import com.Group8.ToDo.Archieve.Service.exception.TaskNotFoundException;
import com.Group8.ToDo.Archieve.Service.exception.UserAlreadyRegistered;
import com.Group8.ToDo.Archieve.Service.exception.UserNotFoundException;
import com.Group8.ToDo.Archieve.Service.model.Task;
import com.Group8.ToDo.Archieve.Service.model.User;
import com.Group8.ToDo.Archieve.Service.repository.UserArchieveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserArchieveServiceImpl implements UserArchieveService{

    private UserArchieveRepository repository;
    private SequenceGenerator sequenceGenerator;
    @Autowired
    public UserArchieveServiceImpl(UserArchieveRepository repository, SequenceGenerator sequenceGenerator) {
        this.repository = repository;
        this.sequenceGenerator = sequenceGenerator;
    }



    @Override
    public User addTask(Task task, String userEmailId) throws UserNotFoundException{
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObject = repository.findByUserEmailId(userEmailId);
        System.out.println("insaid addtask in service" + userObject);
        task.setTaskId(sequenceGenerator.SequenceNumber(Task.SEQUENCE_NAME));
        System.out.println(" After the Sequence");
        System.out.println(task.getTaskId());
        List<Task> tasks;
        if (userObject.getArchieveTask() == null) {
            tasks = new ArrayList<>();

        } else {
            tasks = userObject.getArchieveTask();
//            for (Task task1 : tasks) {
//                if (task1.getTaskHeading().equalsIgnoreCase(task.getTaskHeading())) {
//                    throw new TaskAlreadyExistException();
//                }
//            }
        }

        tasks.add(task);
        userObject.setArchieveTask(tasks);
        return repository.save(userObject);
    }

    @Override
    public User updateTask(String userEmailId, int taskId, Task task) throws TaskNotFoundException, UserNotFoundException {
        User u = repository.findById(userEmailId).get();
        List<Task> tasks=u.getArchieveTask();
        if(repository.findById(userEmailId).isPresent())
        {
            for (Task t:tasks) {
                if(taskId==t.getTaskId())
                {
                    task.setTaskId(taskId);
                   task.setTaskHeading(task.getTaskHeading().substring(0, 1).toUpperCase() + task.getTaskHeading().substring(1));
                   task.setTaskDescription(t.getTaskDescription());
                   task.setTaskStartDate(t.getTaskStartDate());
                   task.setTaskEndDate(t.getTaskEndDate());
                   task.setPriority(t.getPriority());
                    task.setTaskCategory(t.getTaskCategory());
                    tasks.add(task);
                    repository.save(u);
                }
            }
        }
        return repository.save(u);
    }

    @Override
    public List<Task> getAllTask(String userEmailId) throws UserNotFoundException{
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
    public User deleteTask(int taskId, String userEmailId) throws UserNotFoundException {
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
                System.out.println(x);
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
    public User updateProfile(String userEmailId, User user) throws UserNotFoundException {
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObj = repository.findByUserEmailId(userEmailId);
        userObj.setUserName(user.getUserName().substring(0, 1).toUpperCase()+user.getUserName().substring(1));
        userObj.setUserPassword(user.getUserPassword());
        userObj.setDateOfBirth(user.getDateOfBirth());
        userObj.setUserGender(user.getUserGender());
        repository.save(userObj);
        return userObj;
    }

        @Override
    public User registerToArchieve(User user) throws UserAlreadyRegistered {
        if(repository.findById(user.getUserEmailId()).isPresent())
        {
            throw new UserAlreadyRegistered();
        }
        return repository.save(user);
    }

    @Override
    public User forgotPassword(String userEmailId, String userName, User user) throws UserNotFoundException {
        if (repository.findById(userEmailId).isEmpty()) {
            throw new UserNotFoundException();
        }
        User userObj = repository.findByUserEmailIdAndUserName(userEmailId, userName);
        if (userObj == null) {
            throw new UserNotFoundException();
        }
        userObj.setUserPassword(user.getUserPassword());
        return repository.save(userObj);

    }

    //    @Override
//    public List<Task> getIncompletedTask(String userEmailId) throws TaskMentionedBelowException {
//
//        return repository.findById(userEmailId).get().getUserTask().stream().filter(t->!t.isStatus()).collect(Collectors.toList());
//    }
//
//    @Override
//    public List<Task> getCompletedTask(String userEmailId) throws TaskMentionedBelowException {
//
//        return repository.findById(userEmailId).get().getUserTask().stream().filter(t->t.isStatus()).collect(Collectors.toList());
//    }
//    @Override
//    public User removeTaskToRecycle(String userEmailId, int taskId) throws UserNotFoundException, TaskNotFoundException {
//        if (repository.findById(userEmailId).isPresent()) {
//            User userObj=repository.findByUserEmailId(userEmailId);
//            List<Task> userTask=userObj.getUserTask();
//
//            if(userTask.isEmpty())
//            {
//                throw new TaskNotFoundException();
//            }
//            for (Task t:userTask) {
//                if(t.getTaskId()== taskId)
//                {
//                    t.setStatus(false);
////                 t.setCategoryName(task.getCategoryName());
//                    return repository.save(userObj);
//                }
//            }
//        }
//        throw new UserNotFoundException();
//    }

}

//package com.Group8.ToDo.Archieve.Service;
//
//import com.Group8.ToDo.Archieve.Service.exception.UserNotFoundException;
//import com.Group8.ToDo.Archieve.Service.model.Task;
//import com.Group8.ToDo.Archieve.Service.model.User;
//import com.Group8.ToDo.Archieve.Service.repository.UserArchieveRepository;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Arrays;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.Group8.ToDo.Archieve.Service.model.Priority.HIGH;
//import static com.Group8.ToDo.Archieve.Service.model.Priority.LOW;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNotEquals;
//
//@ExtendWith(SpringExtension.class)
//@DataMongoTest
//public class RepositoryTest {
//
//    @Autowired
//    private UserArchieveRepository repository;
//    private User user;
//    private Task task;
//    List<Task> userList;
//    List<Task> archieveList;List<Task> deletedList;
//
//
//
//    @BeforeEach
//    public void setUp() throws ParseException {
//
//        task = new Task(1, "Niit", "001", "Task Test", new Date(), (Date) new SimpleDateFormat("dd/MM/yyyy").parse("04/11/2022"), true, HIGH);
//        user=new User("Rohit@gmail.com","ROhit Soni","Male",new Date(1997-10-27),"ROhit@123",userList,archieveList,deletedList);    }
//
//    @AfterEach
//    public void tearDown() {
//        user = null;
//        task = null;
//        repository.deleteAll();
//    }
//
//    @Test
//    public void givenUserToSaveAndReturnTheUser(){
//        repository.save(user);
//        User user1 = repository.findById(user.getUserEmailId()).get();
//        assertEquals(user.getUserEmailId(),user1.getUserEmailId());
//        assertEquals("RohitSoni",user.getUserName());
//    }
//
//    @Test
//    public void givenUserToSaveAndReturnTheUserFail(){
//        repository.save(user);
//        User user1 = repository.findById(user.getUserEmailId()).get();
//        assertEquals(user.getUserEmailId(),user1.getUserEmailId());
//        assertEquals("RohitSoni",user.getUserName());
//    }
//
//    @Test
//    public void givenUserToSaveAndReturnTheUserFailure() throws UserNotFoundException {
//        repository.save(user);
//        User user1 = repository.findByUserEmailId(user.getUserEmailId());
//        assertNotNull(user1);
//        assertNotEquals(0,user1.getUserName());
//        assertNotEquals(2,user1.getUserName());
//        assertNotEquals("Rohit",user.getUserName());
//    }
//
//
//    //positive test cases for getting all task
//    @Test
//    public void givenTaskSaveTaskToUser() throws UserNotFoundException {
//        user.setArchieveTask( Arrays.asList(task));
//        repository.save(user);
//        User user1 = repository.findByUserEmailId(user.getUserEmailId());
//        List<Task> list =user1.getArchieveTask();
//        assertEquals(1,list.size());
//        assertEquals("Niit",list.get(0).getTaskHeading());
//        assertEquals("001",list.get(0).getTaskCategory());
//        assertEquals("Task Test",list.get(0).getTaskDescription());
//        assertEquals(HIGH,list.get(0).getPriority());
//    }
//
//    //negative cases for getting all task
//    @Test
//    public void givenTaskSaveTaskToUserFailure(){
//        user.setArchieveTask( Arrays.asList(task));
//        repository.save(user);
//        User user1 = repository.findById(user.getUserEmailId()).get();
//        List<Task> list =user1.getArchieveTask();
//        assertNotEquals(3,list.size());
//        assertNotEquals("Nit",list.get(0).getTaskHeading());
//        assertNotEquals("01",list.get(0).getTaskCategory());
//        assertNotEquals("Task ",list.get(0).getTaskDescription());
//        assertNotEquals(LOW,list.get(0).getPriority());
//    }
//
//    //positive test cases for delete cases
//    @Test
//    public void getCompletedTaskOfUser(){
//        task.setStatus(true);
//        user.setArchieveTask(  Arrays.asList(task));
//       repository.save(user);
//        User user1 = repository.findById(user.getUserEmailId()).get();
//        List<Task> list =user1.getArchieveTask().stream().filter(t->t.isStatus()).collect(Collectors.toList());
//        assertEquals(1,list.size());
//        assertEquals("Niit",list.get(0).getTaskHeading());
//        assertEquals("001",list.get(0).getTaskCategory());
//        assertEquals(HIGH,list.get(0).getPriority());
//        assertEquals(true,list.get(0).isStatus());
//    }
//
//    @Test
//    public void getCompletedOfUserFailure() throws UserNotFoundException {
//        user.setArchieveTask(  Arrays.asList(task));
//       repository.save(user);
//        User user1 = repository.findByUserEmailId(user.getUserEmailId());
//        List<Task> list =user1.getArchieveTask().stream().filter(t->t.isStatus()).collect(Collectors.toList());
//        assertNotEquals(2,list.size());
//    }
//
//    @Test
//    public void getDeletedTaskOfUser() throws UserNotFoundException {
//        task.setStatus(true);
//        user.setArchieveTask(  Arrays.asList(task));
//        repository.save(user);
//        User user1 = repository.findByUserEmailId(user.getUserEmailId());
//        List<Task> list =user1.getArchieveTask().stream().filter(t->t.isStatus()).collect(Collectors.toList());
//        assertEquals(1,list.size());
//        assertEquals("Niit",list.get(0).getTaskHeading());
//        assertEquals("001",list.get(0).getTaskCategory());
//        assertEquals(true,list.get(0).isStatus());
//    }
//
//    @Test
//    public void getDeletedTaskOfUserFailure(){
//        user.setArchieveTask(  Arrays.asList(task));
//        repository.save(user);
//        User user1 = repository.findById(user.getUserEmailId()).get();
//        List<Task> list =user1.getArchieveTask().stream().filter(t->!t.isStatus()).collect(Collectors.toList());
//
//    }
//
//}

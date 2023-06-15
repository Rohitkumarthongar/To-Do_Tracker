package com.Group8.ToDo.Archieve.Service.service;

import com.Group8.ToDo.Archieve.Service.exception.TaskAlreadyExistException;
import com.Group8.ToDo.Archieve.Service.exception.TaskNotFoundException;
import com.Group8.ToDo.Archieve.Service.exception.UserAlreadyRegistered;
import com.Group8.ToDo.Archieve.Service.exception.UserNotFoundException;
import com.Group8.ToDo.Archieve.Service.model.Task;
import com.Group8.ToDo.Archieve.Service.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserArchieveService {


    public User addTask(Task task,String userEmailId)  throws UserNotFoundException, TaskAlreadyExistException;
    User deleteTask(int taskId, String userEmailId) throws UserNotFoundException, TaskNotFoundException;
    public List<Task> getAllTask(String userEmailId) throws UserNotFoundException;
    public User forgotPassword(String userEmailId, String userName, User user) throws UserNotFoundException;
    User updateTask(String userEmailId, int taskId, Task task) throws TaskNotFoundException, UserNotFoundException;

         User registerToArchieve(User user) throws UserAlreadyRegistered;
     User updateProfile(String userEmailId, User user) throws  UserNotFoundException;
//    List<Task> getIncompletedTask(String userEmailId) throws TaskMentionedBelowException;
//    List<Task> getCompletedTask(String userEmailId) throws TaskMentionedBelowException;
//    User removeTaskToRecycle(String userEmailId,int taskId) throws UserNotFoundException, TaskNotFoundException;


}

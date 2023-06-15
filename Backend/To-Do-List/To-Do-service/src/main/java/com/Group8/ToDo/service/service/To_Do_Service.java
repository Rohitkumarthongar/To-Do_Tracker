package com.Group8.ToDo.service.service;

import com.Group8.ToDo.service.exceeptin.TaskAlreadyExistException;
import com.Group8.ToDo.service.exceeptin.TaskNotFoundException;
import com.Group8.ToDo.service.exceeptin.UserAlreadyRegistered;
import com.Group8.ToDo.service.exceeptin.UserNotFoundException;
import com.Group8.ToDo.service.model.Task;
import com.Group8.ToDo.service.model.User;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface To_Do_Service {
    User registerUser(User user)throws UserAlreadyRegistered;
    User updateProfile(String userEmailId, User user1) throws UserNotFoundException;

    String getUserName(String userEmailId) throws UserNotFoundException;

    User getUserDetails(String userEmailId) throws UserNotFoundException;

     User forgotPassword(String userName, String userEmailId,User user) throws UserNotFoundException;
    User addTaskToUser(Task task, String userEmailId) throws UserNotFoundException, TaskAlreadyExistException, TaskNotFoundException;
    User ReShiftToUserTask(Task task, String userEmailId,int taskId) throws UserNotFoundException, TaskAlreadyExistException, TaskNotFoundException;
    User shiftToArchieveList(Task task, String userEmailId,int taskId) throws UserNotFoundException, TaskAlreadyExistException, TaskNotFoundException;
    User updateTask(Task task,String userEmailId) throws UserNotFoundException, TaskNotFoundException;
    User CompleteTask(int taskId,String userEmailId) throws UserNotFoundException, TaskNotFoundException;
    User removeTaskToRecycle(Task task, String userEmailId, int taskId) throws UserNotFoundException, TaskNotFoundException;
    public List<Task> getAllArchieveTask(String userEmailId) throws UserNotFoundException;
    User restoreTask(Task task, String userEmailId, int taskId) throws UserNotFoundException, TaskNotFoundException;
    User deleteArTask(int taskId, String userEmailId) throws UserNotFoundException, TaskNotFoundException;
    User deleteDlTask(int taskId, String userEmailId) throws UserNotFoundException, TaskNotFoundException;
    List<Task> getAllTask(String userEmailId) throws UserNotFoundException;
    List<Task> getPendingTask(String userEmailId) throws TaskNotFoundException;
    List<Task> getCompletedTask(String userEmailId) throws TaskNotFoundException;

    public List<Task> getAllDeletedTask(String userEmailId) throws UserNotFoundException;
    List<Task> categorizeByStartDate(String userEmail) throws UserNotFoundException;
    List<Task> categorizeByEndDate(String userEmailId) throws UserNotFoundException;
    List<Task> categorizeByHighPriority(String userEmailId) throws UserNotFoundException;
    List<Task> categorizeByLowPriority(String userEmailId) throws UserNotFoundException;
    Task getTask(String userEmailId, int taskId) throws TaskNotFoundException, UserNotFoundException;
    List<Task> getTasksWithNearDueDate(String userEmail) throws UserNotFoundException;

    public List<Task> getTasksWithOverDue(String userEmail) throws UserNotFoundException;
    List<Task> getTodayTask(String userEmail) throws UserNotFoundException;

    String feedbackForm(String userEmail,String message);


}

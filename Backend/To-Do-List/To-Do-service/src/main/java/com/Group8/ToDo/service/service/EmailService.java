package com.Group8.ToDo.service.service;

import com.Group8.ToDo.service.exceeptin.UserNotFoundException;
import com.Group8.ToDo.service.model.Task;
import com.Group8.ToDo.service.model.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.ParseException;
import java.util.List;

@Service
public interface EmailService {
    String sendSimpleMail(User user);
    String emailReminders() throws UserNotFoundException, MessagingException, ParseException;
    String registerUser (User user);
    String ResetPasswords (User user);
    public String feedBack(String emailId,String message);
     String updateProfile(User user);

    List<Task> getTasksWithNearDueDate(String userEmail) throws UserNotFoundException;

    public List<Task> getTasksWithOverDue(String userEmail) throws UserNotFoundException;
}

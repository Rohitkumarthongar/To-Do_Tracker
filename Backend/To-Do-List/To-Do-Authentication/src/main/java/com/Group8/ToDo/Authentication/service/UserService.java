package com.Group8.ToDo.Authentication.service;

import com.Group8.ToDo.Authentication.exception.UserAlreadyRegistered;
import com.Group8.ToDo.Authentication.exception.UserNotFoundException;
import com.Group8.ToDo.Authentication.model.User;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

@Service
public interface UserService {

    public User saveuserdeatilstodatabase(User user) throws UserAlreadyRegistered;
    User resetPassword (String userEmailId,User user) throws UserNotFoundException;
public User findByUserEmailIdAndUserPassword(String userEmailId,String userPassword) throws UserNotFoundException;
    User updateUser(String userEmailId, User user ) throws UserNotFoundException;

    public String feedbackForm(String userEmailId, String message) throws UserNotFoundException, MessagingException;
}

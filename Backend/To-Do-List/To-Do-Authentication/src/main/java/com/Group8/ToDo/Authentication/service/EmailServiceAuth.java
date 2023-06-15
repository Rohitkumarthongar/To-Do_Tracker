package com.Group8.ToDo.Authentication.service;

import com.Group8.ToDo.Authentication.model.User;

import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public interface EmailServiceAuth {
    public String loginUser(User user);
    String ResetPasswords (User user);

    public String feedBack(String userEmailId,String message) throws MessagingException;

}

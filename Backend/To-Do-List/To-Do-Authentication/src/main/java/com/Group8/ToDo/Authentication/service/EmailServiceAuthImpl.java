package com.Group8.ToDo.Authentication.service;


import com.Group8.ToDo.Authentication.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;


@Service
public class EmailServiceAuthImpl implements EmailServiceAuth {


    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;
    @Autowired
    public EmailServiceAuthImpl(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }
    @Override
    public String loginUser(User user) {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getUserEmailId());
            mailMessage.setText("Wel Come to re-Login,we hope You enjoy tour App. ");
mailMessage.setSubject("User Get Login");
            System.out.println(mailMessage);
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        } catch (Exception e) {
            return "Mail Sent Successfully...";
        }
    }
    @Override
    public String ResetPasswords(User user) {
        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(user.getUserEmailId());
            mailMessage.setText("Pls Apply these Passwords To login ...." + "user Name : " + user.getUserName() + "           User EmailId : " + user.getUserEmailId() + "          User Passwords" + user.getUserPassword());
            mailMessage.setSubject(user.getUserName() + " Get reset Passwords ");
            System.out.println(mailMessage);
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }
        catch (Exception e) {
            return "Mail Sent Successfully...";
        }
    }

    @Override
    public String feedBack(String userEmailId, String message) throws MessagingException {
        String feedbackMessage="Your Feedback Is Valuable For Us,We Will Consider Your Valuable Feed Back in Further Update";
        SimpleMailMessage mailMessage
                = new SimpleMailMessage();
            System.out.println("In try block");
        mailMessage.setFrom(userEmailId);
        mailMessage.setTo(sender);
        mailMessage.setText(message);
        mailMessage.setSubject("Feedback for Improvement ");
        mailMessage.setReplyTo(feedbackMessage);
        System.out.println(mailMessage);
        javaMailSender.send(mailMessage);
        return "Mail Sent Successfully...";


    }
}

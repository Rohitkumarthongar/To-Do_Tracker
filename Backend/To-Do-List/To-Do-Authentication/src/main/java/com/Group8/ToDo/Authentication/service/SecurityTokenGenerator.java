package com.Group8.ToDo.Authentication.service;

import com.Group8.ToDo.Authentication.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public interface SecurityTokenGenerator {
    Map<String ,String> generateToken(User user);
}

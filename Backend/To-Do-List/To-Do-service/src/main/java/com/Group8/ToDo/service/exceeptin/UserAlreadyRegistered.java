package com.Group8.ToDo.service.exceeptin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User Already Exist")
public class UserAlreadyRegistered extends Exception {
}

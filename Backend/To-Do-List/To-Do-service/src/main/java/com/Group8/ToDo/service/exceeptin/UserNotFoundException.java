package com.Group8.ToDo.service.exceeptin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "User Not Found In DAta Base")
public class UserNotFoundException extends Exception{
}

package com.Group8.ToDo.service.exceeptin;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT,reason = "User Already Added This Task")
public class TaskAlreadyExistException extends Exception{
}

package com.Group8.ToDo.Archieve.Service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND,reason = "Task not Found ")
public class TaskNotFoundException extends Exception{
}

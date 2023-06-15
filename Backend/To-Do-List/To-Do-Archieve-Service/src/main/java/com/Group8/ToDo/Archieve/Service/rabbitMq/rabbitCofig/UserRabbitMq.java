package com.Group8.ToDo.Archieve.Service.rabbitMq.rabbitCofig;

import com.Group8.ToDo.Archieve.Service.exception.TaskAlreadyExistException;
import com.Group8.ToDo.Archieve.Service.exception.UserNotFoundException;

import com.Group8.ToDo.Archieve.Service.rabbitMq.TaskDetails;
import com.Group8.ToDo.Archieve.Service.service.UserArchieveServiceImpl;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;

public class UserRabbitMq {
    @Autowired
    private UserArchieveServiceImpl archiveService;

    @RabbitListener(queues = "task_queue")
    public void getCustomerData( TaskDetails details) throws TaskAlreadyExistException, UserNotFoundException {
//        Task task=new Task(details.getTaskId(), details.getTaskHeading(),details.getTaskCategory(),details.getTaskDescription(),details.getTaskEndDate(),details.getTaskStartDate(),details.isStatus(),details.getPriority());
//        archiveService.addTask(task,details.getEmailId());
    }
}

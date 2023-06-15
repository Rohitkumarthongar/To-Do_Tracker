package com.Group8.ToDo.Authentication.config;

import com.Group8.ToDo.Authentication.exception.UserAlreadyRegistered;
import com.Group8.ToDo.Authentication.model.User;
import com.Group8.ToDo.Authentication.rabbitMq.UserDTO;
import com.Group8.ToDo.Authentication.service.UserService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

public class Consumer {
    @Autowired
    UserService userService;

    @RabbitListener(queues="user_queue")
    public void getUserDtoFromRabbitMq(UserDTO userDTO) throws UserAlreadyRegistered {
        User user=new User();
        user.setUserEmailId(user.getUserEmailId());
        user.setUserName(user.getUserName());
        System.out.println("Print UserDto here"+userDTO);
        userService.saveuserdeatilstodatabase(user);
    }
}

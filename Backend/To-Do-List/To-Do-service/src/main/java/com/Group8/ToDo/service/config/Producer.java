package com.Group8.ToDo.service.config;

import com.Group8.ToDo.service.rabbitMQ.UserDTO;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange directExchange;
    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange directExchange) {
        this.rabbitTemplate = rabbitTemplate;
        this.directExchange = directExchange;
    }

    public void sendMessageToRabbitMq(UserDTO taskDetails)
    {
        rabbitTemplate.convertAndSend(directExchange.getName(),"userRouting_key",taskDetails);
    }
}

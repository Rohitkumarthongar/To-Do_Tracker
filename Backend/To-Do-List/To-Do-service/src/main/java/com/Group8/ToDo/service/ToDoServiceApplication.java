package com.Group8.ToDo.service;

import com.Group8.ToDo.service.service.To_Do_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class ToDoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoServiceApplication.class, args);
	}
	private To_Do_Service service;
	@Autowired
	public ToDoServiceApplication(To_Do_Service service) {
		this.service = service;
	}
}

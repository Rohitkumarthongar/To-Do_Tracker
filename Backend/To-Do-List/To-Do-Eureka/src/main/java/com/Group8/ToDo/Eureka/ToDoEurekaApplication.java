package com.Group8.ToDo.Eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ToDoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoEurekaApplication.class, args);
	}

}

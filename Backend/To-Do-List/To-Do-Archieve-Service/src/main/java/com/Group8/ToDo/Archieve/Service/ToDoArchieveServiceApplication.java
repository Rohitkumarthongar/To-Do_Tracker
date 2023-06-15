package com.Group8.ToDo.Archieve.Service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class ToDoArchieveServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoArchieveServiceApplication.class, args);
	}

}

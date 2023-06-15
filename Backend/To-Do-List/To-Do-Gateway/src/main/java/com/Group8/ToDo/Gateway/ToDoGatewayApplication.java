package com.Group8.ToDo.Gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class ToDoGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder.routes().
				route(r -> r.path("/api/to-do/auth/**").uri("lb://To-Do-Auth-Service"))
				.route(r -> r.path("/api/to-do/archieveService/**").uri("lb://To-Do-Archieve-Service"))
				.route(r -> r.path("/api/to-do/toDoService/**").uri("lb://User-To-Do-Service"))
				.build();
	}

}

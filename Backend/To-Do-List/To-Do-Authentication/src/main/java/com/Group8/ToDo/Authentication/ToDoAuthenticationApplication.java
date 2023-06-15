package com.Group8.ToDo.Authentication;
import com.Group8.ToDo.Authentication.filter.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
@EnableEurekaClient
//@EnableHystrix
@SpringBootApplication
public class ToDoAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoAuthenticationApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean jwtFilter()
	{
		FilterRegistrationBean filterRegistrationBean= new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new JwtFilter());
		filterRegistrationBean.addUrlPatterns("/api/to-do/authService/**");
		return filterRegistrationBean;
	}

}

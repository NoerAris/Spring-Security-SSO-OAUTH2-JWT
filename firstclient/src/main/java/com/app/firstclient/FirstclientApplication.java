package com.app.firstclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class FirstclientApplication extends SpringBootServletInitializer{

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FirstclientApplication.class, args);
	}
}
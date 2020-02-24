package com.app.firstclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

@SpringBootApplication
public class FirstclientApplication extends SpringBootServletInitializer{
	
	private static final Logger log = LoggerFactory.getLogger(FirstclientApplication.class);

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}
	
	public static void main(String[] args) {
		log.info("Application is started");
		SpringApplication.run(FirstclientApplication.class, args);
	}
}

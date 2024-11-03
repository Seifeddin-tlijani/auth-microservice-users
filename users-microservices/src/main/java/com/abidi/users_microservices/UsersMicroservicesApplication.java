package com.abidi.users_microservices;


import com.abidi.users_microservices.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class UsersMicroservicesApplication {

	@Autowired
	UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(UsersMicroservicesApplication.class, args);
	}




}

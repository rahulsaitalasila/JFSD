package com.klef.jfsd.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.klef.jfsd.springboot"})
public class UsersdpserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersdpserviceApplication.class, args);
		System.out.println("user sdp microservice prioject");
	}

}

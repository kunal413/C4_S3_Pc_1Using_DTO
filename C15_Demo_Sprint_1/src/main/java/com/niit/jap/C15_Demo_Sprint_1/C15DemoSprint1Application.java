package com.niit.jap.C15_Demo_Sprint_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class C15DemoSprint1Application {

	public static void main(String[] args) {
		SpringApplication.run(C15DemoSprint1Application.class, args);
	}

}

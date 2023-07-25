package com.perscholas.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;


//import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@SpringBootApplication
//hello
@SpringBootApplication
@EnableJpaRepositories("com.perscholas.capstone.repository")
@EntityScan("com.perscholas.capstone.entity")
@ComponentScan(basePackages = "com.perscholas.capstone") 
@ComponentScan(basePackages = "com.perscholas.capstone.controller") 
@ComponentScan(basePackages = "com.perscholas.capstone.controller.CommentController") 
public class CapstoneApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneApplication.class, args);
	}

}



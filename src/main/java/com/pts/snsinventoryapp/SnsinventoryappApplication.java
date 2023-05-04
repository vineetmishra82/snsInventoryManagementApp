package com.pts.snsinventoryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.pts.snsinventoryapp.controller","com.pts.snsinventoryapp.service"})
@EnableMongoRepositories("com.pts.snsinventoryapp.repositories")
public class SnsinventoryappApplication {
	
	
	public static void main(String[] args) {
		System.out.println("Started");
		SpringApplication.run(SnsinventoryappApplication.class, args);
	}

}

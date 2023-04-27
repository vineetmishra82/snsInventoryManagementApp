package com.pts.snsinventoryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SnsinventoryappApplication {
	
	@GetMapping("/")
	public String onSiteLoad() {
		
		return "This is SNS Inventory app, backend loaded...";
	}

	public static void main(String[] args) {
		SpringApplication.run(SnsinventoryappApplication.class, args);
	}

}

package com.pts.snsinventoryapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Document(collection = "admin")
@RequiredArgsConstructor
@Data
public class Admin {

	
	private String userId;
	
	private String password;
	
}

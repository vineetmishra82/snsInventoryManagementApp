package com.pts.snsinventoryapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "categories")
@AllArgsConstructor
@Data
public class Category {
	
	@Getter
	@Setter
	@Id
	private String categoryName;

}

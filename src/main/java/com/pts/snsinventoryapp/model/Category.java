package com.pts.snsinventoryapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "categories")
@RequiredArgsConstructor
@Data
public class Category {
	
	@Getter
	@Setter
	private String categoryName;

}

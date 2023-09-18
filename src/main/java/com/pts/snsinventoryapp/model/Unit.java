package com.pts.snsinventoryapp.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "unit")
@AllArgsConstructor
@Data
public class Unit {
	
	@Getter
	@Setter
	@Id
	private String unitName;
}

package com.pts.snsinventoryapp.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Document(collection = "unit")
@RequiredArgsConstructor
@Data
public class Unit {
	
	private String unitName;
}

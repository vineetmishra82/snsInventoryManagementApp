package com.pts.snsinventoryapp.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "unit")
@RequiredArgsConstructor
@Data
public class Unit {
	
	@Getter
	@Setter
	@NonNull
	private String unitName;
}

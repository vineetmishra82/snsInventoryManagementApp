package com.pts.snsinventoryapp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Document(collection = "items")
@RequiredArgsConstructor
@Data
public class Item {
	
private String itemName;
private String category;
private Unit unit;
private double currentQuantity;
private String remarks;
private String itemId;

public boolean addQuantity(double amount)
{
	if(amount > 0)
	{
		currentQuantity+= amount;
		
		return true;
	}
	else {
		return false;
	}
}

public boolean removeQuantity(double amount)
{
	if(amount <= currentQuantity && currentQuantity>0)
	{
		currentQuantity-= amount;
		
		return true;
	}
	else {
		return false;
	}
}


}

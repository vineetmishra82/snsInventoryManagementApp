package com.pts.snsinventoryapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Document(collection = "tnps")
@AllArgsConstructor
@Data
public class TnP {

	@Getter
	@Setter
	@Id
	private String tnpId;

	@Getter
	@Setter
	private String tnpName;

	@Getter
	@Setter
	private Category category;

	@Getter
	@Setter
	private Unit unit;

	@Getter
	@Setter
	private double currentQuantity;

	@Getter
	@Setter
	private String remarks;

	public boolean addQuantity(double amount) {
		if (amount > 0) {
			currentQuantity += amount;

			return true;
		} else {
			return false;
		}
	}

	public boolean removeQuantity(double amount) {
		if (amount <= currentQuantity && currentQuantity > 0) {
			currentQuantity -= amount;

			return true;
		} else {
			return false;
		}
	}

}

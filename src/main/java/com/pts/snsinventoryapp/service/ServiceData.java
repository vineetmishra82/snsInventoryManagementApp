package com.pts.snsinventoryapp.service;

import java.util.List;

import org.bson.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pts.snsinventoryapp.model.Admin;
import com.pts.snsinventoryapp.model.Category;
import com.pts.snsinventoryapp.model.Unit;
import com.pts.snsinventoryapp.repositories.AdminRepo;
import com.pts.snsinventoryapp.repositories.CategoryRepo;
import com.pts.snsinventoryapp.repositories.UnitRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ServiceData {
	
	private static Logger logger = LoggerFactory.getLogger(ServiceData.class);
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	CategoryRepo categoryRepo;
	
	@Autowired
	UnitRepo unitRepo;

	public boolean adminLogin(String userId, String password) {
		
		List<Admin> admins = adminRepo.findAll();

		for (Admin admin : admins) {

			if (admin.getUserId().equals(userId) && admin.getPassword().equals(password))

				return true;
		}

		return false;
		
		
		
	}

	public boolean addItem(String details) {
		
		JsonObject jsonObject = new JsonObject(details);
		
		
		
		return false;
	}

	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return categoryRepo.findAll();
	}

	public List<Unit> getUnits() {
		// TODO Auto-generated method stub
		return unitRepo.findAll();
	}
	
	
	
	

}

package com.pts.snsinventoryapp.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.message.Message;
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

	private int opsResult;

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

	public int createUnit(String unitName) {
		
		List<Unit> units = unitRepo.findAll();
		
		for (Unit unit : units) {
			if(unit.getUnitName().toLowerCase().equals(unitName.toLowerCase()))
			{
				return -1;
			}
		}
		
		try {
			Unit unit = new Unit(unitName);
			unitRepo.save(unit);
		}
		catch(Exception e)
		{
			return 0;
		}
		
		return 1;
	}

	public int updateUnit(String oldUnitName, String newUnitName) {
		
	Optional<Unit> unit = unitRepo.findById(oldUnitName);
	
	
	unit.ifPresentOrElse((value) -> {
		
		unitRepo.deleteById(oldUnitName);
		unitRepo.save(new Unit(newUnitName));
		
		opsResult = 1;
		
	}, () -> {
		opsResult = -1;
	});
	
	return opsResult;
		
	}

	public int deleteUnit(String unitDelete) {
		

		Optional<Unit> unit = unitRepo.findById(unitDelete);
		
		
		unit.ifPresentOrElse((value) -> {
			
			unitRepo.deleteById(unitDelete);
			
			
			opsResult = 1;
			
		}, () -> {
			opsResult = -1;
		});
		
		return opsResult;
	}
	
	
	
	

}

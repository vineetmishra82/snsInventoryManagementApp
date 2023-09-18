package com.pts.snsinventoryapp.service;

import java.util.List;

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
		
		List<Unit> units = unitRepo.findAll();
		Unit unitSelected = null;
		int index = -1;
		
		for (Unit unit : units) {
			if(unit.getUnitName().toLowerCase().equals(oldUnitName.toLowerCase()))
			{
				unitSelected = unit;
				index = units.indexOf(unit);
				break;
			}
		}
		
		try {
			if(unitSelected != null) {
				unitSelected.setUnitName(newUnitName);
				unitRepo.delete(units.get(index));
				unitRepo.save(unitSelected);
				return 1;
			}
			}catch(Exception e)
			{
				e.printStackTrace();
			}
			
			return -1;
		
	}
	
	
	
	

}

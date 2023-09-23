package com.pts.snsinventoryapp.service;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.message.Message;
import org.bson.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mongodb.client.model.ReturnDocument;
import com.pts.snsinventoryapp.model.Admin;
import com.pts.snsinventoryapp.model.Category;
import com.pts.snsinventoryapp.model.TnP;
import com.pts.snsinventoryapp.model.Unit;
import com.pts.snsinventoryapp.repositories.AdminRepo;
import com.pts.snsinventoryapp.repositories.CategoryRepo;
import com.pts.snsinventoryapp.repositories.TnPRepo;
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
	
	@Autowired
	TnPRepo tnPRepo;

	private int opsResult;
	private int unitsCreated = 0;
	private int categoriesCreated = 0;

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
		
		opsResult = 0;
		
		Optional<Unit> unit = unitRepo.findById(unitDelete);
		
		
		unit.ifPresentOrElse((value) -> {
			
			//Checking if existing in any T&P
			
			for (TnP tp : tnPRepo.findAll()) {
				
				if(tp.getUnit().getUnitName().toLowerCase().equals(unitDelete))
				{
					opsResult = 5;
					break;
				}
				
			}
			
			if(opsResult!=5)
			{
				unitRepo.deleteById(unitDelete);
				
				
				opsResult = 1;
			}
			
			
			
		}, () -> {
			opsResult = 0;
		});
		
		return opsResult;
	}

	public int createCategory(String categoryName) {
		List<Category> categories = categoryRepo.findAll();
		
		for (Category category : categories) {
			if(category.getCategoryName().toLowerCase().equals(categoryName.toLowerCase()))
			{
				return -1;
			}
		}
		
		try {
			Category category = new Category(categoryName);
			categoryRepo.save(category);
		}
		catch(Exception e)
		{
			return 0;
		}
		
		return 1;
	}
	
	public int updateCategory(String oldCategoryName, String newCategoryName) {
		
		Optional<Category> category = categoryRepo.findById(oldCategoryName);
		
		
		category.ifPresentOrElse((value) -> {
			
			categoryRepo.deleteById(oldCategoryName);
			categoryRepo.save(new Category(newCategoryName));
			
			opsResult = 1;
			
		}, () -> {
			opsResult = -1;
		});
		
		return opsResult;
			
		}
	
	public int deleteCategory(String categoryDelete) {
		
		opsResult = 0;

		Optional<Category> category = categoryRepo.findById(categoryDelete);
		
		
		category.ifPresentOrElse((value) -> {
			
			//Checking if existing in any T&P
			
			for (TnP tp : tnPRepo.findAll()) {
				
				if(tp.getCategory().getCategoryName().toLowerCase().equals(categoryDelete.toLowerCase()))
				{
					
					opsResult = 5;
					break;
				}
				
			}
			
			log.info("For matching cat, opsresult is "+opsResult);
			
			if(opsResult!=5)
			{
				categoryRepo.deleteById(categoryDelete);
				
				
				opsResult = 1;
			}		
			
		}, () -> {
			opsResult = 0;
		});
		
		return opsResult;
	}

	public String createTnP(String tnpName, String categoryName, String unitName, double currentQuantity, String remarks) {
		
		categoriesCreated = 0;
		unitsCreated = 0;
		
		try {
			Optional<Unit> unit = unitRepo.findById(unitName);
			
			unit.ifPresentOrElse((value)->{
				
				Optional<Category> category = categoryRepo.findById(categoryName);
				
				
				category.ifPresentOrElse((value1) -> {
					
					String id = "SNS-"+
							(categoryName.length() > 3 ? categoryName.substring(0,3) : categoryName ) +"-"+
							(tnPRepo.count()<1000 ? String.format("%03d", (tnPRepo.count()+1)) :  
									(tnPRepo.count()+1));
					
					boolean isOk = true;
					
					for (TnP tnp : tnPRepo.findAll()) {
						
						if(tnp.getTnpName().toLowerCase().equals(tnpName.toLowerCase()))
						{
							isOk = false;
							break;
						}
						
					}
					
					if(isOk)
					{
						tnPRepo.save(new TnP(id ,tnpName, value1, value, currentQuantity, remarks));						
						
						opsResult = 1;
						
					}
					else {
						opsResult = -1;
					}				
					
				}, () -> {
					if(createCategory(categoryName)==1)
					{
						
						createTnP(tnpName, categoryName, unitName, currentQuantity, remarks);
						categoriesCreated++;
					}
					else {
						opsResult = 0;
					}
				});
				
			},() -> {
				if(createUnit(unitName)==1)
				{
					createTnP(tnpName, categoryName, unitName, currentQuantity, remarks);
					unitsCreated++;
				}
				else {
					opsResult = 0;
				}
				
			});			
			
		}catch (Exception e) {
			opsResult = 0;
		}
		
		String returnValue = opsResult+"_"+categoriesCreated+"_"+unitsCreated;
		return returnValue.trim();
	}

	public List<TnP> getTnP() {
		// TODO Auto-generated method stub
		return tnPRepo.findAll();
	}

	public int updateTnP(String tnpId, String editCategory, String editUnit, String editRemarks) {
		
		Optional<Category> category = categoryRepo.findById(editCategory);
		
		category.ifPresentOrElse((categoryValue) -> {
			
			Optional<Unit> unit = unitRepo.findById(editUnit);
			
			unit.ifPresentOrElse((unitValue)->{
				
				Optional<TnP> tnp = tnPRepo.findById(tnpId);
				
				tnp.ifPresentOrElse((tnpvalue)->{
					
					tnpvalue.setCategory(categoryValue);
					tnpvalue.setUnit(unitValue);
					tnpvalue.setRemarks(editRemarks);
					
					tnPRepo.save(tnpvalue);
					
					opsResult = 1;
					
				}, ()->{
					opsResult = -1;
				});
				
				
				
			}, ()->{
				if(createUnit(editUnit)==1)
				{
					
					updateTnP(tnpId,editCategory,editUnit,editRemarks);
					
				}
				else {
					opsResult = -1;
				}
			});
			
		}, ()-> {
			if(createCategory(editCategory)==1)
			{
				
				updateTnP(tnpId,editCategory,editUnit,editRemarks);
				
			}
			else {
				opsResult = -1;
			}
		});
		
		return opsResult;
	}

	public int deleteTnP(String tnpId) {
		
		try {
			tnPRepo.deleteById(tnpId);
			return 1;
		}catch(Exception e)
		{
			return -1;
		}
	}
	
	
}

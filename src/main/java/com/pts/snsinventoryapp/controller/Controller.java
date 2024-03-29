package com.pts.snsinventoryapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pts.snsinventoryapp.model.Category;
import com.pts.snsinventoryapp.model.TnP;
import com.pts.snsinventoryapp.model.Unit;
import com.pts.snsinventoryapp.service.ServiceData;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class Controller {
	
	private static Logger logger = LoggerFactory.getLogger(Controller.class);
	
	@Autowired
	ServiceData service;

	@GetMapping("/")
	public String onSiteLoad() {
		
		return "This is SNS Inventory app, backend loaded...";
	}
	
	@GetMapping("/adminLogin")
	public boolean adminLogin(@RequestParam String userId,@RequestParam String password)
	{
		return service.adminLogin(userId,password);
	}
	
	@PostMapping("/addItem")
	public boolean addItem(@RequestBody String details)
	{
		return service.addItem(details);		
	}
	
	@GetMapping("/getCategories")
	public List<Category> getCategories()
	{
		return service.getCategories();
	}
	
	@GetMapping("/getUnits")
	public List<Unit> getUnits()
	{
		return service.getUnits();
	}
	
	@PostMapping("/createUnit")
	public int createUnit(@RequestParam String unitName)
	{
		return service.createUnit(unitName);
	}
	
	@PostMapping("/updateUnit")
	public int updateunit(@RequestParam String oldUnitName,@RequestParam String newUnitName)
	{
		return service.updateUnit(oldUnitName,newUnitName);
	}
	
	@DeleteMapping("/unitDelete")
	public int unitDelete(@RequestParam String unitDelete)
	{
		return service.deleteUnit(unitDelete);
	}
	
	@PostMapping("/createCategory")
	public int createCategory(@RequestParam String categoryName)
	{
		return service.createCategory(categoryName);
	}
	
	@PostMapping("/updateCategory")
	public int updateCategory(@RequestParam String oldCategoryName,@RequestParam String newCategoryName)
	{
		return service.updateCategory(oldCategoryName,newCategoryName);
	}
	
	@DeleteMapping("/categoryDelete")
	public int categoryDelete(@RequestParam String categoryDelete)
	{
		return service.deleteCategory(categoryDelete);
	}
	
	@PostMapping("/createTnP")
	public String createTnP(@RequestParam String tnpName,@RequestParam String category,
			@RequestParam String unit,@RequestParam double currentQuantity,@RequestParam String remarks )
	{
		return service.createTnP(tnpName,category,unit,currentQuantity,remarks);
	}
	
	@GetMapping("/getTnP")
	public List<TnP> getTnP()  {
		
		return service.getTnP();
	}
	
	

	@PostMapping("/updateTnP")
	public int updateTnP(@RequestParam String tnpId,@RequestParam String editCategory,
			@RequestParam String editUnit,@RequestParam String editRemarks)
	{
		return service.updateTnP(tnpId,editCategory,editUnit,editRemarks);
	}

	@DeleteMapping("/deleteTnP")
	public int deleteTnP(@RequestParam String tnpId)
	{
		return service.deleteTnP(tnpId);
	}
}

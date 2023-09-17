package com.pts.snsinventoryapp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pts.snsinventoryapp.model.Category;
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
	
	@PostMapping("createUnit")
	public int createUnit(@RequestParam String unitName)
	{
		return service.createUnit(unitName);
	}
}

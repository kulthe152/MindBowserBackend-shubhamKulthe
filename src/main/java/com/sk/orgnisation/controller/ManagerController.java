package com.sk.orgnisation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sk.orgnisation.entity.Manager;
import com.sk.orgnisation.service.ManagerService;

@RestController
public class ManagerController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	@Autowired
	private ManagerService managerService;
	@Autowired	
	private BCryptPasswordEncoder passwordEncoder; 
	

	
	
	
	@CrossOrigin(value = "http://localhost:4200")
	@RequestMapping(value = "/getusers", method = RequestMethod.GET)
	public String getUser(){		
		System.out.println("in User spring boot");
		return "{\"name\":\"Shubham\"}";		
	}
	
	//ManagerRegisteration
	@CrossOrigin(value = "http://localhost:4200")
	@PostMapping("/ManagerRegisteration")
	//@RequestMapping(value = "/ManagerRegisteration", method = RequestMethod.pos)
	public Manager registerManager(@RequestBody Manager manager) throws Exception {
		
		logger.info("inside pagecontroller class and in registerManager method-info");
		logger.debug("inside pagecontroller class and in registerManager method-debug");
		
		String tempEmail = manager.getEmail();
		 if(tempEmail!=null && !"".equals(tempEmail)) {
			 Manager findByEmail = managerService.findByEmail(tempEmail);
			 if(findByEmail !=null)
			 {
				 throw new Exception("Manager with this Email is already exist");
			 }
		 }
		 manager.setPassword(passwordEncoder.encode(manager.getPassword()));
		 
		 System.out.println("after bcrypt :"+manager.getPassword());
		 manager.setRole("MANAGER");
		 Manager registerManager = managerService.RegisterManager(manager);
		return registerManager;
	}	
	
	
}



















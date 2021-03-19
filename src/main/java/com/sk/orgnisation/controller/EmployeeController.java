package com.sk.orgnisation.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sk.orgnisation.entity.Employee;
import com.sk.orgnisation.exception.ResourceNotFoundException;
import com.sk.orgnisation.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@CrossOrigin(value = "http://localhost:4200")
	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public List<Employee> getAllEmployees(){
		
		logger.info("inside EmployeeController class and in getAllEmployees method-info");
		logger.debug("inside EmployeeController class and in getAllEmployees method-debug");
		return employeeRepository.findAll();
	}
	
	@CrossOrigin(value = "http://localhost:4200")
	@PostMapping("/employee")
	public Employee createEmployee(@RequestBody Employee employee) {
		
		logger.info("inside EmployeeController class and in createEmployee method-info");
		logger.debug("inside EmployeeController class and in createEmployee method-debug");
		
		return employeeRepository.save(employee);
	}

	
	@CrossOrigin(value = "http://localhost:4200")
	@GetMapping("/employee/{eid}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable int eid) {
		
		logger.info("inside EmployeeController class and in getEmployeeById method-info");
		logger.debug("inside EmployeeController class and in getEmployeeById method-debug");
		
		Employee employee = employeeRepository.findById(eid)
			.orElseThrow( () -> new ResourceNotFoundException("Employee not exist with id :"+eid));
		return ResponseEntity.ok(employee);
	}
	
	@CrossOrigin(value = "http://localhost:4200")
	@PutMapping("/employee/{eid}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable int eid, @RequestBody Employee employeeDetails){
		
		logger.info("inside EmployeeController class and in updateEmployee method-info");
		logger.debug("inside EmployeeController class and in updateEmployee method-debug");
		
		Employee employee = employeeRepository.findById(eid)
				.orElseThrow( () -> new ResourceNotFoundException("Employee not exist with id :"+eid));
		
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setLastname(employeeDetails.getLastname());
		employee.setAddress(employeeDetails.getAddress());
		employee.setDob(employeeDetails.getDob());
		employee.setMobile(employeeDetails.getMobile());
		employee.setCity(employeeDetails.getCity());		
		Employee updatedEmployee = employeeRepository.save(employee);		
		return ResponseEntity.ok(updatedEmployee);		
	}
	
	
	
	//delete employee rest api
	
	@DeleteMapping("/employee/{eid}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable int eid){
		
		logger.info("inside EmployeeController class and in deleteEmployee method-info");
		logger.debug("inside EmployeeController class and in deleteEmployee method-debug");
		
		Employee employee = employeeRepository.findById(eid)
				.orElseThrow( () -> new ResourceNotFoundException("Employee not exist with id :"+eid));
		
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", true);
		return ResponseEntity.ok(response);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}














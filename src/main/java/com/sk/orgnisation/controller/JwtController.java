package com.sk.orgnisation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sk.orgnisation.SecurtiyConfig.helper.JwtUtil;
import com.sk.orgnisation.model.JwtRequest;
import com.sk.orgnisation.model.JwtResponse;
import com.sk.orgnisation.service.ManagerDetailsService;

@RestController
@CrossOrigin(value = "http://localhost:4200")
public class JwtController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private ManagerDetailsService managerDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@RequestMapping(value = "/token",method = RequestMethod.POST)
	public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	{
		System.out.println(jwtRequest);
		
		try 
		{
			this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(),jwtRequest.getPassword()));
		} 
		catch (UsernameNotFoundException e) 
		{
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		catch(BadCredentialsException e){
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		
		UserDetails userDetails = this.managerDetailsService.loadUserByUsername(jwtRequest.getEmail());
		String generateToken = this.jwtUtil.generateToken(userDetails);
		System.out.println("generateToken : "+generateToken);
		
		return ResponseEntity.ok(new JwtResponse(generateToken));
	}
	
}


///1. in package.json - at 6 line -  "start": "ng serve",



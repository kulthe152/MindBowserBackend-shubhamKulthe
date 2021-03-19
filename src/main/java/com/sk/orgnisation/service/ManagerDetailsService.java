package com.sk.orgnisation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sk.orgnisation.SecurtiyConfig.helper.MyManagerDetails;
import com.sk.orgnisation.entity.Manager;
import com.sk.orgnisation.repository.ManagerRepository;

@Service
public class ManagerDetailsService implements UserDetailsService{

	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Manager findByEmailUsername = managerRepository.findByEmail(username);
		if(findByEmailUsername == null) {
			throw new UsernameNotFoundException(username + "username not found");
		}
		return new MyManagerDetails(findByEmailUsername);
			
	}

}

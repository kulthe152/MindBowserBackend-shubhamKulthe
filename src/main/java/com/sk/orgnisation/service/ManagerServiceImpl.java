package com.sk.orgnisation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sk.orgnisation.entity.Manager;
import com.sk.orgnisation.repository.ManagerRepository;

@Service
public class ManagerServiceImpl implements ManagerService {

	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	public Manager RegisterManager(Manager manager) {
	
		return managerRepository.save(manager);
	}

	@Override
	public Manager findByEmail(String email) {
		return managerRepository.findByEmail(email);		 
	}

}

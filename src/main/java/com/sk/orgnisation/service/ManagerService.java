package com.sk.orgnisation.service;

import com.sk.orgnisation.entity.Manager;

public interface ManagerService {

	Manager RegisterManager(Manager manager);
	Manager findByEmail(String email);
	
}


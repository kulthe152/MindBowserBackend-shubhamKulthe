package com.sk.orgnisation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.orgnisation.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	Manager findByEmail(String email);
	
}

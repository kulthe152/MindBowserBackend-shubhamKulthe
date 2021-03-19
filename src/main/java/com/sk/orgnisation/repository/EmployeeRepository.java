package com.sk.orgnisation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sk.orgnisation.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}

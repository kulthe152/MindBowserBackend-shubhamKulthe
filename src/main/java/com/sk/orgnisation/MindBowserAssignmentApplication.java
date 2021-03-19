package com.sk.orgnisation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sk.orgnisation.controller.ManagerController;

@SpringBootApplication
public class MindBowserAssignmentApplication {

	private static final Logger logger = LoggerFactory.getLogger(ManagerController.class);
	
	public static void main(String[] args) {
		logger.info("main info :: ");
		SpringApplication.run(MindBowserAssignmentApplication.class, args);
	}

}

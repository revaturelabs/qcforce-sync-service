package com.revature;

import org.apache.logging.log4j.LogManager;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * QCForce Sync Service Application Main Class
 * 
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class SyncServiceApplication {
	private static final Logger LOGGER = LogManager.getLogger(SyncServiceApplication.class);

	// LoggerFactory.getLogger(SyncServiceApplication.class);
	/**
	 * @param args input parameters passed in when running the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SyncServiceApplication.class, args);
		LOGGER.info("main: Sync Service Booted Up!");
	}

}

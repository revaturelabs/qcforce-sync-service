package com.revature;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@SpringBootApplication
@EnableJpaRepositories
@EnableScheduling
public class SyncServiceApplication {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(SyncServiceApplication.class, args);
	}

}

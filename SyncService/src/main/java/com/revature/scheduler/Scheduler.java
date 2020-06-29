package com.revature.scheduler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.revature.controllers.SyncController;
import com.revature.service.MessageService;

/**
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@Component
public class Scheduler {
	private static final Logger LOGGER = LogManager.getLogger(Scheduler.class);
	
	private MessageService messageService;

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * 
	 */
	@Scheduled(fixedDelay = 86400000, initialDelay = 300000)
	public void triggerSync() {
		messageService.sendData();
		LOGGER.info("triggerSync: 24 hr scheduled sync initiated.");
	}

}

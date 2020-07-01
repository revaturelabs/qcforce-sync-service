package com.revature.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.revature.AppLogger;
import com.revature.service.MessageService;

/**
 * Schedules a synchronization at a specified interval (default : twenty four hours). 
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Component
public class Scheduler {
	
	/**
	 * Instance of messaging service
	 */
	private MessageService messageService;

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService; 
	}

	/**
	 * Triggers a synchronization with the data source.
	 */
	@Scheduled(fixedDelay = 86400000)
	public void triggerSync() {
		messageService.sendData();
		AppLogger.log.info("triggerSync: 24 hr scheduled sync initiated.");
	}

}

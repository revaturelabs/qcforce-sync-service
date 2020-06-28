package com.revature.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.revature.service.MessageService;

/**
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@Component
public class Scheduler {
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
	}

}

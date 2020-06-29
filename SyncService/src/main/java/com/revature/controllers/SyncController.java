package com.revature.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.SyncServiceApplication;
import com.revature.service.MessageService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Controls all endpoints related to Forms.
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@RestController
public class SyncController {
	private static final Logger LOGGER = LogManager.getLogger(SyncController.class);
	
	/**
	 *	Creates an instance of {@link MessageService} used to send data. 
	 */
	private MessageService messageService;

	/**
	 * Initializes all services.
	 * @param messageService sets from bean of type {@link MessageService}.
	 */
	public SyncController(MessageService messageService) {
		super();
		this.messageService = messageService;
	}
	
	/**
	 * Triggers Sync process to retrieve new data form Google Sheets.
	 * @return a {@link Mono<{@link Void}>} that attempts to trigger the sync process.
	 */
	@PostMapping("/sync")
	public Mono<Void> triggerSyncService() {
		//TODO: Fix Log
		LOGGER.info("triggerSyncService: triggerSyncService Called");
		return Mono.fromRunnable(() -> messageService.sendData()).subscribeOn(Schedulers.elastic()).then();
	}

}

package com.revature.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
	 * @return
	 */
	@PostMapping("/sync")
	public Mono<Void> triggerSyncService() {
		return Mono.fromRunnable(() -> messageService.sendData()).subscribeOn(Schedulers.elastic()).then();
	}

}

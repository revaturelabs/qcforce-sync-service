package com.revature.controllers;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mortbay.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.SyncServiceApplication;
import com.revature.domain.Batch;
import com.revature.service.MessageService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Controls all endpoints related to batches.
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@RestController
public class BatchController {
	private static final Logger LOGGER = LogManager.getLogger(BatchController.class);
	
	/**
	 *	Creates an instance of {@link MessageService} used to send data. 
	 */
	private MessageService messageService;
	
	/**
	 * Initializes all services.
	 * @param messageService sets from bean of type {@link MessageService}.
	 */
	public BatchController(MessageService messageService) {
		super();
		this.messageService = messageService;
	}

	/**
	 * Uploads data from a JSON request and sends it to a messaging cue.
	 * @param data represents the mapped data from a JSON to a {@link List}<{@link Batch}>.
	 * @return a {@link Mono<{@link Void}>} that attempts upload Batch JSON data and send it to a message queue.
	 */
	@PostMapping("/batch")
	public Mono<Void> uploadJSON(@RequestBody List<Batch> data) {
		//TODO: Fix Log
		LOGGER.info("uploadJSON: uploadJson Called");
		return Mono.fromRunnable(() -> messageService.sendBatchData(data)).subscribeOn(Schedulers.elastic()).then();
	}

}

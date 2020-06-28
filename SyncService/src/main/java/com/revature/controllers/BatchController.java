package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Batch;
import com.revature.service.MessageService;

import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@RestController
public class BatchController {

	private MessageService messageService;

	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	/**
	 * @param data
	 * @return
	 */
	@PostMapping("/batch")
	public Mono<Void> uploadJSON(@RequestBody List<Batch> data) {
		return Mono.fromRunnable(() -> messageService.sendBatchData(data)).subscribeOn(Schedulers.elastic()).then();
	}

}

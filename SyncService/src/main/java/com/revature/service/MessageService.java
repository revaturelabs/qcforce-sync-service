package com.revature.service;

import java.util.List;

import com.revature.domain.Batch;

import reactor.core.publisher.Mono;

public interface MessageService {
	public Mono<Void> sendData();

	public void sendBatchData(List<Batch> data);
}

package com.revature.service;

import java.util.List;

import com.revature.domain.Batch;

public interface MessageService {
	public void sendData();

	public void sendBatchData(List<Batch> data);
}

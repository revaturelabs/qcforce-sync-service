package com.revature.service;

import java.util.List;

import com.revature.domain.Batch;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
public interface MessageService {
	
	/**
	 * 
	 */
	public void sendData();

	/**
	 * @param data
	 */
	public void sendBatchData(List<Batch> data);
}

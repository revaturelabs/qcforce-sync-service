package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Batch;
import com.revature.models.FormResponse;
import com.revature.service.GoogleRetrievalService;
import com.revature.service.MessageService;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@RestController
public class SyncController {

	private MessageService messageService;
		
	@Autowired
	public void setMessageService(MessageService messageService)
	{
		this.messageService= messageService;
	}
	
	@PostMapping("/sync")
	public void triggerSyncService()
	{
		messageService.sendData();
	}
	
	
//	@GetMapping("/sync")
//	public List<List<String>> displayCurrentCount()
//	{
//		return googleSheets.getFilteredSheetData();
//	}

}

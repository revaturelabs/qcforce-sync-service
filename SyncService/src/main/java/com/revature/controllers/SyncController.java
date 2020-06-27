package com.revature.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.revature.models.FormResponse;
import com.revature.service.GoogleSheets;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@RestController
public class SyncController {

	/**
	 * @return
	 */
	GoogleSheets googleSheets;
	
	
	
	@Autowired
	public void setGoogleSheets(GoogleSheets googleSheets)
	{
		this.googleSheets = googleSheets;
	}
	
	@PostMapping("/sync")
	public boolean triggerSyncService()
	{
		return googleSheets.sendData();
	}
	
	@GetMapping("/sync")
	public List<FormResponse> displayCurrentCount()
	{
		return googleSheets.getFormResponses();
	}
	
//	@GetMapping("/sync")
//	public List<List<String>> displayCurrentCount()
//	{
//		return googleSheets.getFilteredSheetData();
//	}

}

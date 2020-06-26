package com.revature.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SyncController {

	@PostMapping("/sync")
	public boolean triggerSyncService()
	{
		return true;
	}
	
	
}

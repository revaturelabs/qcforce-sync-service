package com.revature.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@RestController
public class SyncController {

	/**
	 * @return
	 */
	@PostMapping("/sync")
	public boolean triggerSyncService()
	{
		return true;
	}
	
	
}

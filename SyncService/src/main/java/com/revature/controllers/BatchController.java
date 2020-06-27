package com.revature.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.domain.Batch;

@RestController
public class BatchController {

	
	@PostMapping("/batch")
	public void uploadJSON(@RequestBody List<Batch> data)
	{
		System.out.println("In post");
		for(Batch b : data)
		{
			System.out.println(b.toString());
		}
	}
	
	@GetMapping("/batch")
	public void getBatch()
	{
		
	}
	
}

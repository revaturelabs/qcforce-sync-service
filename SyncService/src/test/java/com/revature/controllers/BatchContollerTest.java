package com.revature.controllers;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BatchContollerTest {

	@Autowired
	private BatchController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}

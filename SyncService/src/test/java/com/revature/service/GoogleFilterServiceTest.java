package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest()
class GoogleFilterServiceTest {

	@InjectMocks
	private GoogleFilterImpl googleFilter;

	@Test
	void testRawToString() {
		List<List<Object>> data = new ArrayList<List<Object>>();
		List<List<String>> data2 = new ArrayList<List<String>>();
		assertTrue(googleFilter.convertRawToStringList(data).getClass() == data2.getClass());
	}

}

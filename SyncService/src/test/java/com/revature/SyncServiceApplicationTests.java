package com.revature;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.revature.models.FormResponse;
import com.revature.service.GoogleSheets;

@SpringBootTest
class SyncServiceApplicationTests {

	@Autowired
	GoogleSheets gSheet;

	@Test
	public void testConnectToSpreadSheet() {
		List<FormResponse> data = gSheet.getFormResponses();
		
		for (FormResponse row : data) {
			System.out.println(row.toString());
		}
	}

	public void testMono(List<List<Object>> values) {

	}

}

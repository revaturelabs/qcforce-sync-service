package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.revature.config.SheetsServiceConfig;
import com.revature.models.FormResponse;

@Service
public class GoogleSheets {

	private Sheets sheetsService;
	
	@Autowired
	private void setSheetsService(Sheets sheetsService) {
		this.sheetsService = sheetsService;
	}

	public List<List<String>> getFilteredSheetData() {
		return filterDup(convertRawToStringList(retrieveRawSheetData()));
	}
	
	public List<FormResponse> getFormResponses() {
		List<FormResponse>forms=new ArrayList<FormResponse>();
		List<List<String>> filteredData = getFilteredSheetData();
		List<String>questions=filteredData.get(0);
		questions.remove(0);
		for(int i=1;i< filteredData.size();i++)
		{
			FormResponse temp =new FormResponse();
			temp.setTimestamp(filteredData.get(i).get(0));
			List<String>answers=filteredData.get(i);
			answers.remove(0);
			temp.setQuestions(questions);
			temp.setAnswers(answers);
			temp.setFormId(i);
			forms.add(temp);
		}
		return forms;
	}

	public List<List<Object>> retrieveRawSheetData() {

		String spreadsheetId = SheetsServiceConfig.spreadsheetId;
		String range = "Form Responses 1";

		ValueRange response;
		try {
			response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
			List<List<Object>> values = response.getValues();
			if (values == null || values.isEmpty()) {
				System.out.println("No data found.");
			} else {
				return values;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public List<List<String>> convertRawToStringList(List<List<Object>> data) {
		List<List<String>> listOfLists = new ArrayList<List<String>>();

		for (List row : data) {
			listOfLists.add(row);
		}
		return listOfLists;
	}

	public List<List<String>> filterDup(List<List<String>> data) {
		// size test
//		for (List row : data) {
//			System.out.println("SIZE: " + row.size());
//		}

		List<String> questions = data.get(0);
		List<Integer> itemsToRemove = new ArrayList<Integer>();
		// get index of all duplicate columns to be joined.
		for (int i = 1; i < questions.size() - 1; i++) {
			if (questions.get(i).toString().equals(questions.get(i - 1).toString())
					|| questions.get(i).toString().equals(questions.get(i + 1).toString())) {
				itemsToRemove.add(i);
			}
		}

		// remove duplicate questions
		Set<String> set = new LinkedHashSet<String>();
		set.addAll(questions);
		questions.clear();
		questions.addAll(set);
		data.set(0, questions);

		// Joins duplicate Columns
		for (int i = 1; i < data.size(); i++) {

			for (int j = itemsToRemove.size() - 1; j >= 0; j--) {
				if (data.get(i).get(itemsToRemove.get(j).intValue()).toString().isEmpty()) {
					data.get(i).remove(itemsToRemove.get(j).intValue());
				}
			}
		}

		// fill in missing ending columns

		int maxColumn = data.get(0).size();
		for (List row : data) {
			while (row.size() < maxColumn) {
				row.add("");
			}
		}

		return data;
	}

}

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

/**
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@Service
public class GoogleSheets {

	/** * */
	private Sheets sheetsService;
	
	/** * */
	private int formIdCount = 0;
	
	/**
	 * @param sheetsService
	 */
	@Autowired
	private void setSheetsService(Sheets sheetsService) {
		this.sheetsService = sheetsService;
	}
	// TODO: This should not necessarily be @Autowired given that it's field injection and we shouldn't break encapsulation.
	// However, let's just have it here for now because we might have to store this count in the database. 
	// This would be an easy fix later.
	/**
	 * @param formIdCount
	 */
	@Autowired
	private void setFormIdCount(int formIdCount) {
		this.formIdCount = formIdCount;
	}

	/**
	 * @return
	 */
	public List<List<String>> getFilteredSheetData() {
		return filterDup(convertRawToStringList(retrieveRawSheetData()));
	}
	
	/**
	 * @return
	 */
	public List<FormResponse> getFormResponses() {
		// TODO: Comment
		List<FormResponse>forms=new ArrayList<FormResponse>();
		// TODO: Comment
		List<List<String>> filteredData = getFilteredSheetData();
		// TODO: Comment
		List<String>questions=filteredData.get(0);
		// TODO: Comment
		questions.remove(0);
		
		// TODO: Comment
		for(int i=1;i< filteredData.size();i++)
		{	// TODO: Comment
			FormResponse temp =new FormResponse();
			// TODO: Comment
			temp.setTimestamp(filteredData.get(i).get(0));
			// TODO: Comment
			List<String>answers=filteredData.get(i);
			// TODO: Comment
			answers.remove(0);
			// TODO: Comment
			temp.setQuestions(questions);
			// TODO: Comment
			temp.setAnswers(answers);
			// TODO: Comment
			temp.setFormId(i + formIdCount);
			// TODO: Comment
			forms.add(temp);
		}
		// TODO: Comment
		formIdCount += filteredData.size() - 1;
		// TODO: Comment
		return forms;
	}

	/**
	 * @return
	 */
	public List<List<Object>> retrieveRawSheetData() {
		// TODO: Comment
		String spreadsheetId = SheetsServiceConfig.spreadsheetId;
		// TODO: Comment
		String range = "Form Responses 1";
		// TODO: Comment
		ValueRange response;
		
		// TODO: Comment
		try {
			// TODO: Comment
			response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
			// TODO: Comment
			List<List<Object>> values = response.getValues();
			
			/* 
			 * TODO: Comment
			 */
			if (values == null || values.isEmpty()) {
				System.out.println("No data found.");
			} else {
				return values;
			}
		} catch (IOException e) {
			//TODO: Log this exception
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param data
	 * @return
	 */
	public List<List<String>> convertRawToStringList(List<List<Object>> data) {
		//TODO: Comment
		List<List<String>> listOfLists = new ArrayList<List<String>>();
		
		/* 
		 * TODO: Comment
		 */
		for (List row : data) {
			listOfLists.add(row);
		}
		return listOfLists;
	}

	/**
	 * @param data
	 * @return
	 */
	public List<List<String>> filterDup(List<List<String>> data) {
		// size test
//		for (List row : data) {
//			System.out.println("SIZE: " + row.size());
//		}
		//TODO: Comment
		List<String> questions = data.get(0);
		
		//TODO: Comment
		List<Integer> itemsToRemove = new ArrayList<Integer>();
		
		/* 
		 * Get index of all duplicate columns to be joined.
		 */
		//TODO: Comment
		for (int i = 1; i < questions.size() - 1; i++) {
			//TODO: Comment
			if (questions.get(i).toString().equals(questions.get(i - 1).toString())
					|| questions.get(i).toString().equals(questions.get(i + 1).toString())) {
				
				//TODO: Comment
				itemsToRemove.add(i);
			}
		}
		
		/* 
		 * Remove duplicate questions
		 */
		//TODO: Comment
		Set<String> set = new LinkedHashSet<String>();
		//TODO: Comment
		set.addAll(questions);
		//TODO: Comment
		questions.clear();
		//TODO: Comment
		questions.addAll(set);
		//TODO: Comment
		data.set(0, questions);
		
		/* 
		 * Joins duplicate Columns
		 */
		//TODO: Comment
		for (int i = 1; i < data.size(); i++) {
			//TODO: Comment
			for (int j = itemsToRemove.size() - 1; j >= 0; j--) {
				//TODO: Comment
				if (data.get(i).get(itemsToRemove.get(j).intValue()).toString().isEmpty()) {
					//TODO: Comment
					data.get(i).remove(itemsToRemove.get(j).intValue());
				}
			}
		}
		
		/* 
		 * Fill in missing ending columns
		 */
		//TODO: Comment
		int maxColumn = data.get(0).size();
		//TODO: Comment
		for (List row : data) {
			//TODO: Comment
			while (row.size() < maxColumn) {
				//TODO: Comment
				row.add("");
			}
		}

		return data;
	}

}

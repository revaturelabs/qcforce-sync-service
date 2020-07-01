package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.revature.AppLogger;
import com.revature.config.SheetsServiceConfig;

/**
 * Retrieves raw data from a Google Sheets spreadsheet.
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Service
public class GoogleRetrievalImpl implements DataRetrievalService{
	
	/**
	 *	Instance of a sheet service to connect to Google Sheets. 
	 */
	private Sheets sheetsService;

	/**
	 * Instance of Form Service.
	 */
	private FormService formService;

	/**
	 * The current row in the spreadsheet used to keep track of the response forms that have been sent.
	 */
	public static int currentRow;


	/**
	 * Initializes Services
	 * @param sheetsService Sheet Service bean.
	 * @param formService Form Service bean.
	 */
	public GoogleRetrievalImpl(Sheets sheetsService, FormService formService) {
		super();
		this.sheetsService = sheetsService;
		this.formService = formService;
	}

	@Override
	public List<List<Object>> retrieveRawSheetData() {
		//Sets current spreadsheet. 
		String spreadsheetId = SheetsServiceConfig.SPREAD_SHEET_ID;
		//Gets last row sent.
		currentRow = formService.getFormById(1).getFormId();
		currentRow += 1;
		//Creates range for new responses.
		String range = "A" + (currentRow+1) + ":ZZZ";
		//Creates ranges
		ValueRange response, questions;
		//Populates data into ranges
		try {
			questions = sheetsService.spreadsheets().values().get(spreadsheetId, "A1:1").execute();
			response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
			if (formService.getFormById(1).getFormId() + 1 != currentRow) {
				return new ArrayList<List<Object>>();
			}
			//Gets raw data form ranges
			List<List<Object>> values = response.getValues(); 
			//Returns retrieved data if any.
			values.add(0, questions.getValues().get(0));
			return values;
	
		} catch (Exception e) {
			AppLogger.log.error("Nothing to retrieve.");
			return new ArrayList<List<Object>>();
		}

	}

}

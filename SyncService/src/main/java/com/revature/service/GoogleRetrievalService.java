package com.revature.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import com.revature.config.RabbitMQConfig;
import com.revature.config.SheetsServiceConfig;
import com.revature.domain.Form;
import com.revature.models.FormResponse;

/**
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@Service
public class GoogleRetrievalService implements DataRetrievalService{

	/** * */
	private Sheets sheetsService;
	
	public static int currentRow;
	
	/** * */
	private FormService formService;
	
	/**
	 * @param sheetsService
	 */
	@Autowired
	public void setSheetsService(Sheets sheetsService) {
		this.sheetsService = sheetsService;
	}
	
	/**
	 * @param formService
	 */
	@Autowired
	public void setFormService(FormService formService) {
		this.formService = formService;
	}
	
	/**
	 * @return
	 */
	@Override
	public List<List<Object>> retrieveRawSheetData() {
		// TODO: Comment
		String spreadsheetId = SheetsServiceConfig.spreadsheetId;
		// TODO: Comment
		
		currentRow = formService.getFormById(1).getFormId();
		
		if (currentRow<=0)
		{
			currentRow=1;
		}
		
		String range = "A"+(currentRow+1)+":ZZZ";
		// TODO: Comment
		ValueRange response,questions;
		
		// TODO: Comment
		try {
			questions = sheetsService.spreadsheets().values().get(spreadsheetId, "A1:1").execute();
			response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
			
			if(formService.getFormById(1).getFormId()+1!=currentRow)
			{
				return new ArrayList<List<Object>>();
			}
			
			List<List<Object>> values = response.getValues();
			if(values!=null)
			{
				values.add(0, questions.getValues().get(0));
				return values;
			}
			else
			{
				return new ArrayList<List<Object>>();
			}
			
		} catch (Exception e) {
			//TODO: Log this exception
			e.printStackTrace();
			return new ArrayList<List<Object>>();
		}

	}

}

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
public class GoogleSheets {

	/** * */
	private Sheets sheetsService;
	
	
	/** * */
	private FormService formService;
	
	private RabbitTemplate rabbitTemplate;
	
	private MessageConverter messageConverter;
	
	/**
	 * @param sheetsService
	 */
	@Autowired
	private void setSheetsService(Sheets sheetsService) {
		this.sheetsService = sheetsService;
	}
	
	/**
	 * @param rabbitTemplate
	 */
	@Autowired
	private void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	/**
	 * @param messageConverter
	 */
	@Autowired
	private void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}
	
	/**
	 * @param formService
	 */
	@Autowired
	private void setFormService(FormService formService) {
		this.formService = formService;
	}

	
	public boolean sendData()
	{
		rabbitTemplate.setMessageConverter(messageConverter);
		List<FormResponse> data = this.getFormResponses();
		for (FormResponse row : data) {
			System.out.println(row.toString());
			rabbitTemplate.convertAndSend(RabbitMQConfig.exchange,RabbitMQConfig.routingKey,row);
		}
		return true;
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
		
		
		List<FormResponse>forms=new ArrayList<FormResponse>();
		
		List<List<String>> filteredData = getFilteredSheetData();
		
		System.out.println("Filtered data size: "+filteredData.size());
		
		if (filteredData.size()==0)
		{
			return new ArrayList<FormResponse>();
		}
		// TODO: Comment
		List<String>questions=filteredData.get(0);
		// TODO: Comment
		questions.remove(0);
		
		//Cycle through filtered data and create a new form response and add it to the returned array
		for(int i=1;i< filteredData.size();i++)
		{
			FormResponse temp =new FormResponse();
			temp.setTimestamp(filteredData.get(i).get(0));
			List<String>answers=filteredData.get(i);
			answers.remove(0);
			temp.setQuestions(questions);
			temp.setAnswers(answers);
			
			forms.add(temp);
		}
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
		
		int currentRow = formService.getFormById(1).getFormId();
		
		if (currentRow<=0)
		{
			currentRow=1;
		}
		
		System.out.println("Current Row : "+currentRow);
		
		String range = "A"+(currentRow+1)+":ZZZ";
		// TODO: Comment
		ValueRange response,questions;
		
		// TODO: Comment
		try {
			questions = sheetsService.spreadsheets().values().get(spreadsheetId, "A1:1").execute();
			// TODO: Comment
			response = sheetsService.spreadsheets().values().get(spreadsheetId, range).execute();
			// TODO: Comment
			List<List<Object>> values = response.getValues();
			
			currentRow+=values.size();
			Form f =new Form();
			f.setId(1);
			f.setFormId(currentRow);
			formService.updateForm(f);
			
			values.add(0, questions.getValues().get(0));
			/* 
			 * TODO: Comment
			 */
			System.out.println(values);
			
			return values;
			
		} catch (Exception e) {
			//TODO: Log this exception
			e.printStackTrace();
			return new ArrayList<List<Object>>();
		}

	}

	/**
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<List<String>> convertRawToStringList(List<List<Object>> data) {
		//TODO: Comment
		List<List<String>> listOfLists = new ArrayList<List<String>>();
		/* 
		 * TODO: Comment
		 */
		for (@SuppressWarnings("rawtypes") List row : data) {
			listOfLists.add(row);
		}
		return listOfLists;
	}

	/**
	 * @param data
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<List<String>> filterDup(List<List<String>> data) {
		if(data.size()==0)
		{
			return new ArrayList<List<String>>();
		}
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
		for (@SuppressWarnings("rawtypes") List row : data) {
			//TODO: Comment
			while (row.size() < maxColumn) {
				//TODO: Comment
				row.add("");
			}
		}

		return data;
	}

	public FormService getFormService() {
		return formService;
	}
	
	

}

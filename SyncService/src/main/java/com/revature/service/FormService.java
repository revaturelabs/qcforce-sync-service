package com.revature.service;

import java.util.List;

import com.revature.domain.Form;


public interface FormService {
	
		public List<Form> getAllForms();
		
		public Form getFormById(int id);
		
		public void createForm(Form question);
		
		public void updateForm(Form question);
		
		public void deleteForm(Form question);
		

}

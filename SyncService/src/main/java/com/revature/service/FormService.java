package com.revature.service;

import java.util.List;

import com.revature.domain.Form;


/**
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
public interface FormService {
	
	/**
	 * @return
	 */
	public List<Form> getAllForms();
		
	/**
	 * @param id
	 * @return
	 */
	public Form getFormById(int id);
		
	/**
	 * @param question
	 */
	public void createForm(Form question);
		
	/**
	 * @param question
	 */
	public void updateForm(Form question);
		
	/**
	 * @param question
	 */
	public void deleteForm(Form question);
		

}

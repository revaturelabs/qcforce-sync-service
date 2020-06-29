package com.revature.service;

import java.util.List;

import com.revature.models.FormResponse;

/**
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
public interface DataFilterService {
	
	/**
	 * @return
	 */
	public List<List<String>> getFilteredSheetData();
	
	/**
	 * @param data
	 * @return
	 */
	public List<List<String>> convertRawToStringList(List<List<Object>> data);
	
	/**
	 * @param data
	 * @return
	 */
	public List<List<String>> filterDup(List<List<String>> data);
	
	/**
	 * @return
	 */
	public List<FormResponse> mapFormResponses();
}

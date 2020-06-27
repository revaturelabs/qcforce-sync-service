package com.revature.service;

import java.util.List;

import com.revature.models.FormResponse;

public interface DataFilterService {
	public List<List<String>> getFilteredSheetData();
	public List<List<String>> convertRawToStringList(List<List<Object>> data);
	public List<List<String>> filterDup(List<List<String>> data);
	public List<FormResponse> mapFormResponses();
}

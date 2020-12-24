package com.revature.service;

import com.revature.models.Survey;

/**
 * 
 * @author 
 * Hannah
 * Brett
 * Alma
 * Yarashlee
 *
 */

public interface SurveyService {

	/**
	 * Get SurveyView 
	 * @return 
	 */
	
	public Survey getSurvey(int id);
	
	public Survey createSurvey(Survey survey);
	
	public Survey updateSurvey(Survey survey);
	
	public int deleteSurvey(Survey survey);

}

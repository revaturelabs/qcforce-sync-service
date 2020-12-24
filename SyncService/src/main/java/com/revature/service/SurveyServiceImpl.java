package com.revature.service;

import com.revature.models.Survey;
import com.revature.repo.SurveyRepo;

public class SurveyServiceImpl implements SurveyService {

	SurveyRepo surveyRepo;
	
	@Override
	public Survey getSurvey(int id) {
		// TODO Auto-generated method stub
		return surveyRepo.getSurvey(id);
		
	}

	@Override
	public Survey createSurvey(Survey survey) {
		// TODO Auto-generated method stub
		return surveyRepo.createSurvey(survey);
	}

	@Override
	public Survey updateSurvey(Survey survey) {
		// TODO Auto-generated method stub
		return surveyRepo.updateSurvey(survey);
	}

 	@Override
	public int deleteSurvey(Survey survey) {
		// TODO Auto-generated method stub
		return surveyRepo.deleteSurvey(survey);
	}


}

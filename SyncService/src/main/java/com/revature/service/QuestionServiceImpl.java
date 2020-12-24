/**
 * 
 */
package com.revature.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.models.SurveyQuestion;
import com.revature.repo.QuestionRepo;

/**
 * @author Work From Home
 *
 */
@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepo questionRepo;

	@Override
	public SurveyQuestion getSurveyQuestion(int id) {
		
		return null;
	}

}

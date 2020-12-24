/**
 * 
 */
package com.revature.controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dto.SurveyQuestionDto;
import com.revature.models.QuestionType;
import com.revature.models.SurveyQuestion;
import com.revature.service.QuestionService;

/**
 * @author Work From Home
 *
 */
@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest
class QuestionControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private QuestionService service;
	
	private SurveyQuestion surveyQuestion;
	
	private String surveyQuestionJson;
	
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		
		List<String> questions = new ArrayList<String>();
		questions.add("How are you?");
		surveyQuestion = new SurveyQuestion(1, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1, questions);
		
		// writing value as a Json string
		ObjectMapper om	= new ObjectMapper();
		surveyQuestionJson = om.writeValueAsString(new SurveyQuestionDto(surveyQuestion));
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void questionControllerTest_WithoutError() {
		
		Mockito.when(service.getSurveyQuestion(surveyQuestion.getId())).thenReturn(surveyQuestion);
		
		// expecting request
		try {
			
			this.mockMvc.perform(get("/question/" + surveyQuestion.getId()))
				.andExpect(status().isOk())
				.andExpect(content().json(surveyQuestionJson));
			
		} catch (Exception e) {
			
			fail("Exception thrown during call: " + e);
			
		}
		
	}
	
	@Test
	void questionControllerTest_InvalidInput() {
		
		Mockito.when(service.getSurveyQuestion(surveyQuestion.getId())).thenReturn(null);
		
		try {
			
			this.mockMvc.perform(get("/question/" + surveyQuestion.getId()))
					.andExpect(status().isBadRequest())
					.andExpect(content().json(""));		
			
		} catch (Exception e) {
			
			fail("Exception thrown during call: " + e);
			
		}
		
	}

}

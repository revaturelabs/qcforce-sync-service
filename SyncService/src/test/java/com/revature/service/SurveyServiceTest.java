package com.revature.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.event.annotation.AfterTestClass;
import org.springframework.test.context.event.annotation.BeforeTestClass;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.models.QuestionType;
import com.revature.models.Survey;
import com.revature.models.SurveyQuestion;
import com.revature.repo.SurveyRepo;


@SpringBootTest()
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class SurveyServiceTest {
	
	@Mock
	
	SurveyRepo surveyRepo;
	@MockBean
	private SurveyQuestion surveyQuestion;
	private Survey survey;
	
	@InjectMocks
	SurveyServiceImpl surveyService;
	
	@BeforeTestClass
	public static void setUpBeforeClass() throws Exception {
		
	}
	@AfterTestClass
	public static void tearDownAfterClass() throws Exception {
		
	}
	
	@BeforeEach
	public void setUp() throws Exception {
		
		List<String> questions = new ArrayList<String>();
		questions.add("How are you?");
		List<SurveyQuestion> surveyQuestionList = new ArrayList<>();
		surveyQuestion = new SurveyQuestion(1, LocalDateTime.now(), QuestionType.SHORT_ANSWER, 1, questions);
		Survey survey = new Survey(1, 8, "Kubernetes", 30, LocalDateTime.now(), surveyQuestionList);
		
	}
	
	/**
	 * Happy path, creates survey
	 * @author Hannah, Alma, Brett, and Yara
	 */
	
	@Test
	void surveyServiceTest_WithoutError() {
		
		when(surveyRepo.createSurvey(survey)).thenReturn(survey);
		
		Survey returned = surveyService.createSurvey(survey);
		
		verify(surveyRepo).createSurvey(survey);
		
		assertEquals(survey, returned, "Object returned does not match expected.");
		
	}
	
	/*
	 * Happy path for getting a survey
	 * Can't retrieve survey because it doesn't exist
	 * @author Hannah, Alma, Brett, and Yara
	 */
	
	@Test
	void surveyServiceTest_RetrievedWithoutError(){
		
		when(surveyRepo.getSurvey(survey.getId())).thenReturn(survey);
		
		Survey returned = surveyService.getSurvey(survey.getId());
		
		verify(surveyRepo).getSurvey(survey.getId());
		
		assertEquals(survey, returned, "Object returned does not match expected.");
	}
	
	/*
	 * Edge case #1
	 * Can't retrieve survey because it doesn't exist
	 * @author Hannah, Alma, Brett, and Yara
	 */
	
	@Test
	void surveyServiceTest_SurveyDoesntExist() throws NullPointerException {
		
		when(surveyRepo.getSurvey(survey.getId())).thenReturn(null);
		
		assertThrows(NullPointerException.class, () -> surveyService.getSurvey(survey.getId()));
	}
	
	
	/**
	 * Edge case #2
	 * Cannot create survey because it is null
	 * @author Hannah, Alma, Brett, and Yara
	 */
	@Test
	void surveyServiceTest_CannotCreateSurvey() {
		
		survey = null;
		
		when(surveyRepo.createSurvey(survey)).thenReturn(null);
		
		assertThrows(NullPointerException.class, () -> surveyService.createSurvey(survey));
		
	}
	
	
	/**
	 * Happy path; successfully deleting a survey
	 * @author Hannah, Alma, Brett, and Yara
	 */
	
	@Test
	void surveyServiceTest_DeleteSurveyWithoutErrors() {
		
		when(surveyRepo.deleteSurvey(survey)).thenReturn(1);
		
		verify(surveyRepo).deleteSurvey(survey);
		
		assertEquals(surveyService.deleteSurvey(survey), 1);
		
	}
	
	/**
	 * Edge case #3
	 * Cannot delete survey because it doesn't exist
	 * @author Hannah, Alma, Brett, and Yara
	 */
	
	@Test
	void surveyServiceTest_CannotDeleteSurvey() {
		

		when(surveyRepo.deleteSurvey(survey)).thenReturn(0);
		
		assertEquals(surveyService.deleteSurvey(survey), 0);
		
	}
	
	
	@AfterEach
	public void tearDown() throws Exception {
	}
	
}
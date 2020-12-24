package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Work From Home
 *
 */

public class SurveyQuestion {
	
	 	private int id;
	    
	    private LocalDateTime createdOn;
	    
	    private QuestionType type;
	    
	    private int version;
	    
	    private List<String> question;

	    public SurveyQuestion() {
	        super();
	    }

	    /**
	     * @param id
	     * @param createdOn
	     * @param type
	     * @param version
	     * @param question
	     */
	    public SurveyQuestion(int id, LocalDateTime createdOn, QuestionType type, int version, List<String> question) {
	        super();
	        this.id = id;
	        this.createdOn = createdOn;
	        this.type = type;
	        this.version = version;
	        this.question = question;
	    }

	    /**
	     * @return the id
	     */
	    public int getId() {
	        return id;
	    }

	    /**
	     * @param id the id to set
	     */
	    public void setId(int id) {
	        this.id = id;
	    }

	    /**
	     * @return the createdOn
	     */
	    public LocalDateTime getCreatedOn() {
	        return createdOn;
	    }

	    /**
	     * @param createdOn the createdOn to set
	     */
	    public void setCreatedOn(LocalDateTime createdOn) {
	        this.createdOn = createdOn;
	    }

	    /**
	     * @return the type
	     */
	    public QuestionType getType() {
	        return type;
	    }

	    /**
	     * @param type the type to set
	     */
	    public void setType(QuestionType type) {
	        this.type = type;
	    }

	    /**
	     * @return the version
	     */
	    public int getVersion() {
	        return version;
	    }

	    /**
	     * @param version the version to set
	     */
	    public void setVersion(int version) {
	        this.version = version;
	    }

	    /**
	     * @return the question
	     */
	    public List<String> getQuestion() {
	        return question;
	    }

	    /**
	     * @param question the question to set
	     */
	    public void setQuestion(List<String> question) {
	        this.question = question;
	    }    
	    

}

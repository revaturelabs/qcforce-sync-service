package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
public class FormResponse implements Serializable {

	/** * */
	private static final long serialVersionUID = 9136762341724971453L;

	/** * */
	private int formId;
	
	/** * */
	private String timestamp;
	
	/** * */
	private List<String> questions = new ArrayList<String>();
	
	/** * */
	private List<String> answers = new ArrayList<String>();

	/**
	 * @return
	 */
	public int getFormId() {
		return formId;
	}

	/**
	 * @param formId
	 */
	public void setFormId(int formId) {
		this.formId = formId;
	}

	/**
	 * @return
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return
	 */
	public List<String> getQuestions() {
		return questions;
	}

	/**
	 * @param questions
	 */
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	/**
	 * @return
	 */
	public List<String> getAnswers() {
		return answers;
	}

	/**
	 * @param answers
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	/**
	 *
	 */
	@Override
	public String toString() {
		return "FormResponse [formId=" + formId + ", timestamp=" + timestamp + ", questions=" + questions + ", answers="
				+ answers + "]";
	}
	
	
}

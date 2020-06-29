package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *Standard template of a QC survey form that will be sent through a message queue.
 *@author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
public class FormResponse implements Serializable {

	/**
	 * Serializable version UID
	 */
	private static final long serialVersionUID = 9136762341724971453L;

	/**
	 * variable of type {@link Integer} that represents the id linked to a form response. 
	 */
	private int formId;

	/**
	 * variable of type {@link String} that represents the time the response was submited. 
	 */
	private String timestamp;

	/**
	 * variable of type {@link List}{@link String} that represents the questions of the response. 
	 */
	private List<String> questions;

	/**
	 * variable of type {@link List}{@link String} that represents the answers to the questions of the response. 
	 */
	private List<String> answers;

	/**
	 * initializes the questions and answers arrays.
	 */
	public FormResponse() {
		super();
		this.questions = new ArrayList<String>();
		this.answers = new ArrayList<String>();
	}

	/**
	 * Gets the form id.
	 * @return form id.
	 */
	public int getFormId() {
		return formId;
	}

	/**
	 * Sets the form id.
	 * @param formId new form id.
	 */
	public void setFormId(int formId) {
		this.formId = formId;
	}

	/**
	 * Gets the time stamp for the form.
	 * @return form time stamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * Sets the form time stamp.
	 * @param timestamp new time stamp.
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Gets an {@link List}{@link String} of questions from the form.
	 * @return {@link List}{@link String} questions.
	 */
	public List<String> getQuestions() {
		return questions;
	}

	/** Sets the form questions.
	 * @param questions new {@link List}{@link String} of questions.
	 */
	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	/**
	 * Gets an {@link List}{@link String} of answers to the questions from the form.
	 * @return {@link List}{@link String} answers.
	 */
	public List<String> getAnswers() {
		return answers;
	}

	/** Sets the form answers.
	 * @param answers new {@link List}{@link String} of answers.
	 */
	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return "FormResponse [formId=" + formId + ", timestamp=" + timestamp + ", questions=" + questions + ", answers="
				+ answers + "]";
	}

}

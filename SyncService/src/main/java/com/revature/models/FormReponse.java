package com.revature.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FormReponse implements Serializable {

	private static final long serialVersionUID = 9136762341724971453L;

	private int formId;
	private String timestamp;
	private List<String> questions = new ArrayList<String>();
	private List<String> answers = new ArrayList<String>();

	public int getFormId() {
		return formId;
	}

	public void setFormId(int formId) {
		this.formId = formId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getQuestions() {
		return questions;
	}

	public void setQuestions(List<String> questions) {
		this.questions = questions;
	}

	public List<String> getAnswers() {
		return answers;
	}

	public void setAnswers(List<String> answers) {
		this.answers = answers;
	}
}

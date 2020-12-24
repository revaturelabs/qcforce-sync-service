package com.revature.models;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 
 * @author 
 * Hannah 
 * Alma 
 * Brett
 * Yarashlee
 *
 */

public class Survey {
	
	private int id;
	
	private int version;
	
	private String title; 
	
	private int createdBy;
	
	private LocalDateTime createdOn;
	
	private List<SurveyQuestion> question;

	/**
	 * @param id
	 * @param version
	 * @param title
	 * @param createdBy
	 * @param createdOn
	 * @param question
	 */
	public Survey(int id, int version, String title, int createdBy, LocalDateTime createdOn,
			List<SurveyQuestion> question) {
		super();
		this.id = id;
		this.version = version;
		this.title = title;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.question = question;
	}

	/**
	 * 
	 */
	public Survey() {
		super();
		// TODO Auto-generated constructor stub
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
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
	 * @return the question
	 */
	public List<SurveyQuestion> getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(List<SurveyQuestion> question) {
		this.question = question;
	}

	@Override
	public String toString() {
		return "Survey [id=" + id + ", version=" + version + ", title=" + title + ", createdBy=" + createdBy
				+ ", createdOn=" + createdOn + ", question=" + question + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + createdBy;
		result = prime * result + ((createdOn == null) ? 0 : createdOn.hashCode());
		result = prime * result + id;
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + version;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Survey other = (Survey) obj;
		if (createdBy != other.createdBy)
			return false;
		if (createdOn == null) {
			if (other.createdOn != null)
				return false;
		} else if (!createdOn.equals(other.createdOn))
			return false;
		if (id != other.id)
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (version != other.version)
			return false;
		return true;
	}
	
	
	
	
		

	
	

}

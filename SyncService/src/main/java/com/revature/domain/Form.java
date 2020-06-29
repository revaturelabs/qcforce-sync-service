package com.revature.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * This class represents form data from form submissions.
 * @authors Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 */
@Entity
@Table(name= "form")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property ="id")
public class Form implements Serializable{

	
	/**
	 *	Serializable version UID 
	 */
	private static final long serialVersionUID = 3333672074531118712L;

	/**
	 *	variable of type {@link Integer} that identifies the row in the database that tracks form id's. 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	/**
	 *	variable of type {@link Integer} that represents the id of the last form sent to the Rabbit Message Queue. 
	 */
	@Column(name = "form_id")
	private int formId;

	
	/**
	 * Gets the id of the database row that tracks a form's id.
	 * @return database row id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the id of the database row that tracks a form's id.
	 * @param id new database row id.
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the id of the last form sent to the Rabbit Message Queue.
	 * @return id of the last form sent to the Rabbit Message Queue.
	 */
	public int getFormId() {
		return formId;
	}

	/**
	 * Sets the id of the last form sent to the Rabbit Message Queue
	 * @param formId new form id
	 */
	public void setFormId(int formId) {
		this.formId = formId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + formId;
		result = prime * result + id;
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
		Form other = (Form) obj;
		if (formId != other.formId)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Form [id=" + id + ", formId=" + formId + "]";
	}


}

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
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@Entity
@Table(name= "form")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property ="id")
public class Form implements Serializable{

	/** * */
	private static final long serialVersionUID = 3333672074531118712L;

	/** * */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	int id;

	/** * */
	@Column(name = "form_id")
	int formId;

	/**
	 * @param id
	 * @param formId
	 */
	public Form(int id, int formId) {
		super();
		this.id = id;
		this.formId = formId;
	}

	/**
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

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

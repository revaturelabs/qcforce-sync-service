package com.revature.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * @author Wei Wu, Andres Mateo Toledo Albarracin, Jose Canela
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"email",
	"firstName",
	"lastName"
})
public class Employee {

	/** * */
	@JsonProperty("email")
	private String email;
	/** * */
	@JsonProperty("firstName")
	private String firstName;
	/** * */
	@JsonProperty("lastName")
	private String lastName;
	/** * */
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	/**
	 * @return
	 */
	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 */
	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return
	 */
	@JsonProperty("firstName")
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 */
	@JsonProperty("firstName")
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return
	 */
	@JsonProperty("lastName")
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 */
	@JsonProperty("lastName")
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return
	 */
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	/**
	 * @param name
	 * @param value
	 */
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	@Override
	public String toString() {
		return "Employee [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", additionalProperties=" + additionalProperties + "]";
	}

}
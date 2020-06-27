package com.revature.domain;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"role",
"employee",
"deletedAt"
})
public class EmployeeAssignment {

@JsonProperty("role")
private String role;
@JsonProperty("employee")
private Employee employee;
@JsonProperty("deletedAt")
private Object deletedAt;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("role")
public String getRole() {
return role;
}

@JsonProperty("role")
public void setRole(String role) {
this.role = role;
}

@JsonProperty("employee")
public Employee getEmployee() {
return employee;
}

@JsonProperty("employee")
public void setEmployee(Employee employee) {
this.employee = employee;
}

@JsonProperty("deletedAt")
public Object getDeletedAt() {
return deletedAt;
}

@JsonProperty("deletedAt")
public void setDeletedAt(Object deletedAt) {
this.deletedAt = deletedAt;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

@Override
public String toString() {
	return "EmployeeAssignment [role=" + role + ", employee=" + employee + ", deletedAt=" + deletedAt
			+ ", additionalProperties=" + additionalProperties + "]";
}

}
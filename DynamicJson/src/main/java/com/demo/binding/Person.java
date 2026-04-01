package com.demo.binding;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
	
	Integer id;
	String name;
	Details details;
	List<String> hobbies;
	@JsonProperty("extraField")
	ExtraFields extraFields;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Details getDetails() {
		return details;
	}
	public void setDetails(Details details) {
		this.details = details;
	}
	public List<String> getHobbies() {
		return hobbies;
	}
	public void setHobbies(List<String> hobbies) {
		this.hobbies = hobbies;
	}
	public ExtraFields getExtraFields() {
		return extraFields;
	}
	public void setExtraFields(ExtraFields extraFields) {
		this.extraFields = extraFields;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", details=" + details + ", hobbies=" + hobbies
				+ ", extraFields=" + extraFields + "]";
	}

}

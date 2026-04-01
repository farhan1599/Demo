package com.demo.binding;

import java.util.List;

public class User {

	private int id;
	private String name;
	private List<String> skills;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", skills=" + skills + "]";
	}
	

}

package com.demo.binding;

public class Details {

	String email;
	Long phone;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return "Details [email=" + email + ", phone=" + phone + "]";
	}
	
	
	
}

package com.abhishek.response;

import com.abhishek.entity.USER_ROLE;

public class AuthResponse {

	private String jwt;
	
	private String massage;
	private USER_ROLE role;
	
	
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public String getMassage() {
		return massage;
	}
	public void setMassage(String massage) {
		this.massage = massage;
	}
	public USER_ROLE getRole() {
		return role;
	}
	public void setRole(USER_ROLE role) {
		this.role = role;
	}
	public AuthResponse(String jwt, String massage, USER_ROLE role) {
		super();
		this.jwt = jwt;
		this.massage = massage;
		this.role = role;
	}
	public AuthResponse() {
		super();
	}
	@Override
	public String toString() {
		return "AuthResponse [jwt=" + jwt + ", massage=" + massage + ", role=" + role + "]";
	}
	
	
}

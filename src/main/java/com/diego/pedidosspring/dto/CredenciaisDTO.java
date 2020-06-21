package com.diego.pedidosspring.dto;

public class CredenciaisDTO {

	private String userName;
	private String password;
	
	public CredenciaisDTO() { }
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

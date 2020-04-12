package com.springboot.api.model;

/**
 * Class representing the request for the User
 * 
 * @author dfsalinas
 *
 */
public class RequestUser {

	private String username;

	private String password;

	public RequestUser(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}

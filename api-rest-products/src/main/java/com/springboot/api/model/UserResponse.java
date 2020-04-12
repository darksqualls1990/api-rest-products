package com.springboot.api.model;

/**
 * Exclusive class for user response
 * 
 * @author dfsalinas
 *
 */
public class UserResponse {

	String username;

	String token;

	String message;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

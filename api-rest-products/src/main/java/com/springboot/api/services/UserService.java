package com.springboot.api.services;

import com.springboot.api.model.User;

/**
 * Interface containing all the methods for user CRUD
 * 
 * @author dfsalinas
 *
 */
public interface UserService {

	/**
	 * Allows to obtain a token for authentication
	 * 
	 * @param userName
	 * @return
	 */
	public String getJwtToken(String userName);

	/**
	 * Allows to consult user by name
	 * 
	 * @param username
	 * @return
	 */
	public User getUserByUsername(String username);

	/**
	 * Create user
	 * 
	 * @param userNew
	 * @return
	 */
	public User createUser(User userNew);

}

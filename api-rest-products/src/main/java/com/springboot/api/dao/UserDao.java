package com.springboot.api.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.springboot.api.model.User;

/**
 * DAO that handles persistence operations for users
 * 
 * @author dfsalinas
 *
 */
public interface UserDao extends JpaRepository<User, Integer> {

	/**
	 * Get user by username
	 * 
	 * @param username
	 * @return
	 */
	@Query("select u from User u where u.username = :username")
	Optional<User> findUserByUsername(String username);

}

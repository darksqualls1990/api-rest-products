package com.springboot.api.rest.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.api.model.User;
import com.springboot.api.model.RequestUser;
import com.springboot.api.model.UserResponse;
import com.springboot.api.services.UserService;

/**
 * REST controller for Users
 * 
 * @author dfsalinas
 *
 */
@RestController
public class UserController {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserService userService;

	/**
	 * Supports user and password authentication
	 * 
	 * @param userRequest
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody RequestUser userRequest) {
		UserResponse response = new UserResponse();
		if (userRequest != null && userRequest.getPassword() != null && userRequest.getUsername() != null) {
			User user = userService.getUserByUsername(userRequest.getUsername());
			if (user != null && user.getPassword() != null
					&& passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
				String token = userService.getJwtToken(user.getUsername());
				response.setToken(token);
				response.setUsername(user.getUsername());
				response.setMessage("OK");
				return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			} else {
				response.setMessage("Wrong username or password!");
				response.setUsername(userRequest.getUsername());
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}
		}
		response.setMessage("Unauthenticated user, missing data");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles creating users
	 * 
	 * @param userRequest
	 * @return
	 */
	@PostMapping("/user/create")
	public ResponseEntity<?> create(@RequestBody @Valid RequestUser userRequest) {
		UserResponse response = new UserResponse();
		if (userRequest != null && userRequest.getPassword() != null && userRequest.getUsername() != null) {
			User userNew = new User();
			userNew.setPassword(passwordEncoder.encode(userRequest.getPassword()));
			userNew.setUsername(userRequest.getUsername());
			userNew = userService.createUser(userNew);
			if (userNew != null && userNew.getCodigo() != null) {
				response.setUsername(userNew.getUsername());
				response.setMessage("User created");
				return new ResponseEntity<>(response, HttpStatus.CREATED);
			} else {
				response.setMessage("Product not created");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		}
		response.setMessage("User not created, missing data");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

}

package com.springboot.api.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.api.constants.Constantes;
import com.springboot.api.dao.UserDao;
import com.springboot.api.model.User;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Implementation class for user CRUD
 * 
 * @author dfsalinas
 *
 */
@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public String getJwtToken(String userName) {
		String token = Jwts.builder().setId("testJWT").setSubject(userName)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + Constantes.TOKEN_EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, Constantes.SUPER_SECRET_KEY.getBytes()).compact();
		return Constantes.TOKEN_BEARER_PREFIX + token;
	}

	@Override
	public User getUserByUsername(String username) {
		Optional<User> user = userDao.findUserByUsername(username);
		if (user.isPresent()) {
			return user.get();
		}
		return null;
	}

	@Override
	public User createUser(User userNew) {
		if (userNew != null) {
			return userDao.save(userNew);
		}
		return null;
	}

}

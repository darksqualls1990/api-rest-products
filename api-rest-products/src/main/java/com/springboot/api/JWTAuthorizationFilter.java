package com.springboot.api;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.springboot.api.constants.Constantes;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

/**
 * Filter for API authorization
 * 
 * @author dfsalinas
 *
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String token = req.getHeader(Constantes.PARAM_AUTHORIZACION_KEY);
		if (token == null || !token.startsWith(Constantes.TOKEN_BEARER_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}
		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	/**
	 * Get the authentication token from request
	 * 
	 * @param request
	 * @return
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader(Constantes.PARAM_AUTHORIZACION_KEY);
		if (token != null) {
			try {
				// Procesa el token y retorna el usuario.
				String user = Jwts.parser().setSigningKey(Constantes.SUPER_SECRET_KEY.getBytes())
						.parseClaimsJws(token.replace(Constantes.TOKEN_BEARER_PREFIX, "")).getBody().getSubject();
				if (user != null) {
					return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
				}
			} catch (ExpiredJwtException e) {
				return null;
			}
		}
		return null;
	}
}

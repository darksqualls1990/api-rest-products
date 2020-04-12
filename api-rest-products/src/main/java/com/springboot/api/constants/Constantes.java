package com.springboot.api.constants;

/**
 * General constants required in the application
 * 
 * @author dfsalinas
 *
 */
public final class Constantes {

	public static final String PARAM_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	public static final String SUPER_SECRET_KEY = "secretKeyTest";
	public static final Integer TOKEN_EXPIRATION_TIME = 600000;
	public static final String LOGIN_URL = "/login";
	public static final String PRODUCT_PUBLIC_URL = "/product/public/**";
	public static final String CATEGORY_PUBLIC_URL = "/category/public/**";

}

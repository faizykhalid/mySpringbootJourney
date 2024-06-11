package com.fasols.chatapp.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtils {
	
	private static final String SECRET = "myjwtsecret";
	private static final Long DEFAULT_EXPIRATION_TIME = 3600l;
	private static final String PASSWORD_CLAIM = "pass";
	
	public static String generateToken(String username, String password) {
		return JWT.create().withSubject(username)
				.withClaim(PASSWORD_CLAIM, password)
				.withExpiresAt(new Date(System.currentTimeMillis() + DEFAULT_EXPIRATION_TIME))
				.sign(Algorithm.HMAC256(password));
	}
	
	public static String[] extractUserAndPassword(String token) throws JWTVerificationException {
		String[] userAndPassword = new String[2];
		DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
		userAndPassword[0] = decodedJwt.getSubject();
		userAndPassword[1] = decodedJwt.getClaim(PASSWORD_CLAIM).asString();
		return userAndPassword;
	}

}

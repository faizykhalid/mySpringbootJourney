package com.fasols.chatapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Strings;
import lombok.Getter;

import java.util.Date;


public class JWTToken {
	
	private static final String SECRET = "myjwtsecret";
	private static final Long DEFAULT_EXPIRATION_TIME = 360000L;
	private static final String PASSWORD_CLAIM = "pass";

	@Getter
	private String token;

	public JWTToken(String username, String password) {
		JWTToken token = new JWTToken(username,password,0);
		this.token = token.getToken();
	}

	public JWTToken(String username, String password, long expiryTime) {
		this.token = JWT.create().withSubject(username)
				.withClaim(PASSWORD_CLAIM, password)
				.withExpiresAt(new Date(System.currentTimeMillis() + Math.max(DEFAULT_EXPIRATION_TIME, expiryTime) ))
				.sign(Algorithm.HMAC256(SECRET));
	}
	
	public static String[] extractUserAndPassword(String token) throws JWTVerificationException {
		String[] userAndPassword = new String[2];
		DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
		userAndPassword[0] = decodedJwt.getSubject();
		userAndPassword[1] = decodedJwt.getClaim(PASSWORD_CLAIM).asString();
		return userAndPassword;
	}

	public DecodedJWT getDecodedJWT() {
		return getDecodedJWT(this.token, null);
	}

	public DecodedJWT getDecodedJWT(String secret) {
		return getDecodedJWT(this.token, secret);
	}

	public static DecodedJWT getDecodedJWT(String token, String secret) {
		return JWT.require(Algorithm.HMAC256(Strings.isNullOrEmpty(secret) ? SECRET : secret))
				.build().verify(token);
	}

}

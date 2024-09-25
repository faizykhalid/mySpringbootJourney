package com.fasols.chatapp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.base.Strings;
import lombok.Getter;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;


public class JWTToken {
	
	private static final String SECRET = "myjwtsecret";
	private static final Long DEFAULT_EXPIRATION_TIME = 10L; // 10 minutes
	private static final String PASSWORD_CLAIM = "pass";

	@Getter
	private String token;

	public JWTToken(String username, String password) {
		JWTToken token = new JWTToken(username,password, 0);
		this.token = token.getToken();
	}

	public JWTToken(String username, String password, long expiryTime) {
		this.token = JWT.create().withSubject(username)
				.withClaim(PASSWORD_CLAIM, password)
				.withExpiresAt(Instant.ofEpochSecond(Instant.now().plus(Duration.ofMinutes(Math.max(DEFAULT_EXPIRATION_TIME, expiryTime))).toEpochMilli()))
				.sign(Algorithm.HMAC256(SECRET));
	}
	
	public static String[] extractUserAndPassword(String token) throws JWTVerificationException {
		String[] userAndPassword = new String[2];
		DecodedJWT decodedJwt = getDecodedJWT(token, SECRET);
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

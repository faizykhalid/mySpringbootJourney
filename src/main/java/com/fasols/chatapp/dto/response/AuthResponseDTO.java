package com.fasols.chatapp.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthResponseDTO implements Serializable {

	public AuthResponseDTO(String em, String tok, String expiry) {
		this.email = em;
		this.token = tok;
		this.readableExpiryTime = expiry;
	}

	public AuthResponseDTO(String em, String tok, Long expiry) {
		this.email = em;
		this.token = tok;
		this.expiryTime = expiry;
	}

	@Serial
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = -2886641118494910866L;

	private String email;
	private String token;
	private Long expiryTime;
	private String readableExpiryTime;

}

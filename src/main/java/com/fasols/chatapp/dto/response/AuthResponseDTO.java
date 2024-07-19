package com.fasols.chatapp.dto.response;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Data
@AllArgsConstructor
public class AuthResponseDTO implements Serializable {

	@Serial
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = -2886641118494910866L;

	private String email;
	private String token;
	private Long expiryTime;

}

package com.fasols.chatapp.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Data()
public class LoginRequestDTO implements Serializable {

	@Serial
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private static final long serialVersionUID = 2369419473533047212L;

	private String email;
	private String password;

}

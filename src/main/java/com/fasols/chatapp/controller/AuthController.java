package com.fasols.chatapp.controller;

import com.fasols.chatapp.util.JWTToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasols.chatapp.dto.request.LoginRequestDTO;
import com.fasols.chatapp.dto.response.AuthResponseDTO;

import java.time.Instant;

@RestController
@RequestMapping("auth")
public class AuthController {

    @PostMapping("/signin")
    public AuthResponseDTO getSignOnToken(@RequestBody LoginRequestDTO request) {
        JWTToken token = new JWTToken(request.getEmail(), request.getPassword());
        Long expiry = token.getDecodedJWT().getClaims().get("exp").asLong();
    	return new AuthResponseDTO(
                request.getEmail(), token.getToken(), Instant.ofEpochMilli(expiry).toString()
        );
    }
}

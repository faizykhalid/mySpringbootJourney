package com.fasols.chatapp.controller;

import com.fasols.chatapp.common.response.GenericResponse;
import com.fasols.chatapp.dto.request.LoginRequestDTO;
import com.fasols.chatapp.dto.response.AuthResponseDTO;
import com.fasols.chatapp.service.AuthService;
import com.fasols.chatapp.util.JWTToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public AuthResponseDTO getSignOnToken(@RequestBody LoginRequestDTO request) {
        JWTToken token = new JWTToken(request.getEmail(), request.getPassword());
        Long expiry = token.getDecodedJWT().getClaims().get("exp").asLong();
    	return new AuthResponseDTO(
                request.getEmail(), token.getToken(), Instant.ofEpochSecond(expiry).toString()
        );
    }

    @PostMapping("/forget-password")
    public ResponseEntity<GenericResponse<String>> resetPassword(@RequestBody LoginRequestDTO request) {
        this.authService.resetPassword(request.getEmail());
        return ResponseEntity.ok(new GenericResponse<>("Success", "Reset Link has been emailed", null));
    }
    


}

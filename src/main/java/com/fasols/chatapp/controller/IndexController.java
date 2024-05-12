package com.fasols.chatapp.controller;

import com.fasols.chatapp.dto.response.UserResponseDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class IndexController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getHome(){
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping(path = "index",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getIndex(){
        return this.getHome();
    }
}

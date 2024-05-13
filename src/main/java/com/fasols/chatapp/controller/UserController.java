package com.fasols.chatapp.controller;

import com.fasols.chatapp.dto.response.UserResponseDTO;
import com.fasols.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDTO getUser(@PathVariable("userId") String userId) {
        return this.userService.getUserById(userId);
    }

    @GetMapping("")
    public List<UserResponseDTO> getUser() {
        return this.userService.getAllUsers();
    }
}

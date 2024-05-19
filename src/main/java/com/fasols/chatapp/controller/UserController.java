package com.fasols.chatapp.controller;

import com.fasols.chatapp.dto.request.UserRequestDTO;
import com.fasols.chatapp.dto.response.UserResponseDTO;
import com.fasols.chatapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Page<UserResponseDTO> getPaginatedUser(@RequestParam Integer pageSize,
                                                  @RequestParam(defaultValue = "0", name = "pageNo", required = false) Integer pageNumber) {
//        if (pageSize == null || pageSize.compareTo(0) < 1) {
//            return ResponseEntity.badRequest();
//        } else {
            Pageable pageToFetch = PageRequest.of(Optional.ofNullable(pageNumber).orElse(0), pageSize);
            return this.userService.getAllUsers(pageToFetch);
//        }
    }

    public List<UserResponseDTO> getAllUsers(){
        return this.userService.getAllUsers();
    }

    @PostMapping("")
    public UserResponseDTO createUser(@RequestBody UserRequestDTO user) {
        return this.userService.createUser(user);
    }
}

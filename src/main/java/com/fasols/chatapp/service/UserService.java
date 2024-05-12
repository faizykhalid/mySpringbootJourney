package com.fasols.chatapp.service;

import com.fasols.chatapp.dao.UserRepository;
import com.fasols.chatapp.dto.response.UserResponseDTO;
import com.fasols.chatapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    public final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepo = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> getAllUsers() {
        return this.userRepo.findAll().stream()
                .map(userMapper::userToUserResponseDTO).toList();
    }

    public UserResponseDTO getUserById(String userId) {
        return this.userRepo.findById(userId)
                .map(userMapper::userToUserResponseDTO).orElse(null);
    }
}

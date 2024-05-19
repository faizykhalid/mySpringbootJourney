package com.fasols.chatapp.service;

import com.fasols.chatapp.dao.UserRepository;
import com.fasols.chatapp.dto.request.UserRequestDTO;
import com.fasols.chatapp.dto.response.UserResponseDTO;
import com.fasols.chatapp.entity.User;
import com.fasols.chatapp.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepo;
    public final UserMapper userMapper;
    @Autowired
    private ApplicationContext applicationContext;


    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepo = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponseDTO> getAllUsers() {
        return this.userRepo.findAll().stream()
                .map(userMapper::userToUserResponseDTO).toList();
    }

    public Page<UserResponseDTO> getAllUsers(Pageable pageInfo) {
        Page<User> paginatedUsers = this.userRepo.findAll(pageInfo);
        return paginatedUsers.map(userMapper::userToUserResponseDTO);
    }

    public UserResponseDTO getUserById(String userId) {
        return this.userRepo.findById(userId)
                .map(userMapper::userToUserResponseDTO).orElse(null);
    }

    public UserResponseDTO createUser(UserRequestDTO user) {
        User newUser = userMapper.userRequestdtoToUser(user);
        PasswordEncoder passwordEncoder = applicationContext.getAutowireCapableBeanFactory().getBean(PasswordEncoder.class);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        return userMapper.userToUserResponseDTO(
                this.userRepo.save(newUser)
        );
    }
}

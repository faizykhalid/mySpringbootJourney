package com.fasols.chatapp.service;

import com.fasols.chatapp.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepo;
	
	@Autowired
	public CustomUserDetailsService(UserRepository userRepository) {
		this.userRepo = userRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<com.fasols.chatapp.entity.User> fetchedUser =  this.userRepo.findById(username);
		if (fetchedUser.isEmpty()) throw new UsernameNotFoundException("Invalid Username");
		return new User(fetchedUser.get().getEmail(), fetchedUser.get().getPassword(), Collections.EMPTY_LIST);
	}

}

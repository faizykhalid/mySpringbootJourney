package com.fasols.chatapp.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasols.chatapp.dao.UserRepository;

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
		return new User(fetchedUser.get().getUsername(), fetchedUser.get().getPassword(), Collections.EMPTY_LIST);
	}

}

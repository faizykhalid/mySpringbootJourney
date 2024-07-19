package com.fasols.chatapp.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fasols.chatapp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}

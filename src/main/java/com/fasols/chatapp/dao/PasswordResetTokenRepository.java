package com.fasols.chatapp.dao;

import com.fasols.chatapp.entity.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, PasswordResetToken.TokenKey> {

}

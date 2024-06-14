package com.fasols.chatapp.entity;

import java.time.Instant;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

//import com.fasols.chatapp.enums.TokenType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    private String username;
    private String name;
    @Column(nullable = false)
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(columnDefinition = "CHAR(1) CHECK (ENABLED IN ('Y','N') ) NOT NULL")
    Character enabled;
    private String token;
//    @Enumerated(EnumType.ORDINAL)
//    private TokenType tokenType;
    private Instant tokenExpiry;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant updateDate;
}

package com.fasols.chatapp.entity;

import com.fasols.chatapp.enums.TokenType;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;
import org.hibernate.annotations.UuidGenerator;

import lombok.Data;
import org.springframework.context.annotation.Primary;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

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
    @Enumerated(EnumType.ORDINAL)
    private TokenType tokenType;
    private Instant tokenExpiry;
    @CreatedDate
    private Instant createdDate;
    @LastModifiedDate
    private Instant updateDate;
}

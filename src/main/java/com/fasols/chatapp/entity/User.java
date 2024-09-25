package com.fasols.chatapp.entity;

import com.fasols.chatapp.enums.TokenType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @UuidGenerator
    private UUID id;
    private String name;
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(columnDefinition = "CHAR(1) CHECK (ENABLED IN ('Y','N') ) NOT NULL Default 'Y'")
    Character enabled = 'Y';
    private String token;
    @Enumerated(EnumType.ORDINAL)
    private TokenType tokenType;
    private Instant tokenExpiry;
    @CreationTimestamp
    @CreatedDate
    private Instant createdDate;
    @UpdateTimestamp
    @LastModifiedDate
    private Instant updateDate;
}

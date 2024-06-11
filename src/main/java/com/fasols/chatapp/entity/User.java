package com.fasols.chatapp.entity;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @UuidGenerator
    private String id;
    private String name;
    private String password;
    @Column(unique = true, nullable = false)
    private String email;
}

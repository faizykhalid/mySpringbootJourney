package com.fasols.chatapp.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

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

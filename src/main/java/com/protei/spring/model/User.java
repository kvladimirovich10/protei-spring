package com.protei.spring.model;


import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @Column(nullable = false, length = 30)
    private String username;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    @Column(unique = true, nullable = false, length = 50)
    private String phoneNumber;

    public User(String username, String email, String phoneNumber) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}

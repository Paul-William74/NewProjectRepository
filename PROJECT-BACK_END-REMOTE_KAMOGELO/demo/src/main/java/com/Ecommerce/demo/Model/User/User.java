package com.Ecommerce.demo.Model.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false, length = 50)
    protected String firstName;

    @Column(nullable = false, length = 50)
    protected String lastName;

    @Column(nullable = false, length = 50, unique = true)
    protected String email;

    @Column(length = 12)
    protected String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    protected USER_TYPE userType;

    @Column(nullable = false)
    protected String password;

    //more will be added later
}

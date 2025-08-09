package com.Ecommerce.demo.Model.WorkEmail;

import jakarta.persistence.*;
import lombok.Data;

@Entity @Data
public final class WorkEmail {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String appPassword;

    public WorkEmail(String email, String appPassword) {
        this.email = email;
        this.appPassword = appPassword;
    }
}

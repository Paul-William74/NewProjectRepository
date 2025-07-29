package com.Ecommerce.demo.Model.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 7)
    private String postalCode;

    @Column(nullable = false, length = 40)
    private String city;

    @Column(nullable = false, length = 20)
    private String province;

    @Column(nullable = false, length = 50)
    private String street;

    @ManyToOne @JoinColumn(nullable = false)
    private User user;
}

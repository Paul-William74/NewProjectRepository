package com.Ecommerce.demo.Model.Product;


import com.Ecommerce.demo.Model.User.Admin;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
public final class Brand {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String imgUrl;

    @OneToOne @JoinColumn(nullable = false)
    private Admin admin; //brand cannot exist without an Admin behind it
}

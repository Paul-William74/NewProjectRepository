package com.Ecommerce.demo.Model.Product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public final class ProductImage {

    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(nullable = false)
    private ProductMaterial productMaterial;

    @Column(nullable = false)
    private String imgUrl;
}

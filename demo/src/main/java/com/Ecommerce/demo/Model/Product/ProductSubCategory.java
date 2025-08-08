package com.Ecommerce.demo.Model.Product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public final class ProductSubCategory {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Product product;

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private RING_TYPE ringType;
}

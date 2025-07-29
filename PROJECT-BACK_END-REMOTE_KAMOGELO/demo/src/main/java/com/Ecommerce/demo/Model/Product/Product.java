package com.Ecommerce.demo.Model.Product;


import com.Ecommerce.demo.Model.Compare.ProductsCompare;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.mapping.Join;

import java.util.LinkedList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public final class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @ManyToOne @JoinColumn(nullable = false)
    private Brand brand;

    @OneToMany(orphanRemoval = true)
    private final List<ProductsCompare> productsCompareList = new LinkedList<>();
}

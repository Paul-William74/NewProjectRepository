package com.Ecommerce.demo.Model.Product;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
public final class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, length = 200)
    private String description;

    @Column(nullable = false)
    private boolean onDiscount = false;

    @Column(nullable = false)
    private boolean isActive = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private JEWELERY_TYPE jeweleryType;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @CollectionTable(name = "product_gemstones", joinColumns = @JoinColumn(name = "product_id"))
    @ElementCollection(targetClass = GEMSTONE.class)
    @Enumerated(EnumType.STRING)
    private final Set<GEMSTONE> gemStones = new HashSet<>();

    /// one to many relationships
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductSize> productSizes = new LinkedList<>(); // List of product sizes available for this product

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductImage> productImages = new LinkedList<>(); // List of images associated with this product

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ProductPrice> productPrices = new LinkedList<>();

    public Product(String name, String description, JEWELERY_TYPE jeweleryType) {
        this.name = name;
        this.description = description;
        this.jeweleryType = jeweleryType;
    }
}

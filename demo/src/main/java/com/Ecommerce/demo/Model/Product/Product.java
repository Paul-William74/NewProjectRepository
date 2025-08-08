package com.Ecommerce.demo.Model.Product;


import com.Ecommerce.demo.Model.Compare.ProductsCompare;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    @CollectionTable(name = "product_gemstone", joinColumns = @JoinColumn(name = "product_id"))
    @ElementCollection(targetClass = GEMSTONE.class)
    @Enumerated(EnumType.STRING)
    private Set<GEMSTONE> gemstones = new HashSet<>();

    @Column(nullable = false)
    private Double price; // the price of the product

    @Column(nullable = false)
    private boolean onDiscount = false; // if the product is on discount or not

    @Column(nullable = false)
    private double discountPercentage = 0.0; // the percentage of discount on the product

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "product")
    private final List<ProductSize> productSizes = new LinkedList<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "product")
    private final List<ProductMaterial> productMaterials = new LinkedList<>();

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "product")
    private final List<ProductsCompare> productsCompareList = new LinkedList<>();

}

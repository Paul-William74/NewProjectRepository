package com.Ecommerce.demo.Model.Cart;

import com.Ecommerce.demo.Model.Product.MATERIAL;
import com.Ecommerce.demo.Model.Product.ProductPrice;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public final class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private ProductPrice productPrice;

    @Column(nullable = false)
    private Double subTotal;

    @CollectionTable(name = "cart_item_material", joinColumns = @JoinColumn(name = "cart_item_id"))
    @ElementCollection(targetClass = MATERIAL.class)
    @Enumerated(EnumType.STRING)
    private Set<MATERIAL> material = new HashSet<>();

    @ManyToOne @JoinColumn(nullable = false)
    private Cart cart;

}

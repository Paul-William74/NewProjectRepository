package com.Ecommerce.demo.Model.Cart;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public final class CartItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    //reference to that specific product(could be a product size)
    //private Double unitPrice = productSize.getPrice() || product.getPrice(); // unknown if there will be a size until specialising

    @Column(nullable = false)
    private Double subTotal;

    @ManyToOne @JoinColumn(nullable = false)
    private Cart cart;

}

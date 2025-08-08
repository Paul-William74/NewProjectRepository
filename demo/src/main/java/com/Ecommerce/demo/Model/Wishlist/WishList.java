package com.Ecommerce.demo.Model.Wishlist;

import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "product_size_id"})
)
public final class WishList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private ProductSize productSize;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Customer customer;
}

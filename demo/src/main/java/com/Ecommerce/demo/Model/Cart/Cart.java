package com.Ecommerce.demo.Model.Cart;

import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public final class Cart {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean isChecked = false;

    @ManyToOne @JoinColumn(nullable = false)
    private Customer customer;

    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "cart")
    List<CartItem> cartItemList = new LinkedList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) //active upon initial creation
    private CART_STATUS cartStatus = CART_STATUS.ACTIVE;

    @Column(nullable = false)
    private Double total = 0.0;

    @Transient //not stored in the database
    public static final Double TAX = 0.15;

}

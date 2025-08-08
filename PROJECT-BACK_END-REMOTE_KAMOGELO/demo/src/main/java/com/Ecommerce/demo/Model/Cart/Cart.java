package com.Ecommerce.demo.Model.Cart;

import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.Cleanup;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    //list of cartItems with  (many to one)

    @Enumerated(EnumType.STRING)
    @Column(nullable = false) //active upon initial creation
    private CART_STATUS cartStatus = CART_STATUS.ACTIVE;

    @Column(nullable = false)
    private Double total = 0.0;

    @Transient //not stored in the database
    public static final Double TAX = 0.15;

}

package com.Ecommerce.demo.Model.WaitingList;


import com.Ecommerce.demo.Model.Product.ProductSize;
import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "product_size_id"})
)
public final class WaitingList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private ProductSize productSize;

    //if the customer has been notified if that product is back
    private boolean hasBeenNotified = false;

}

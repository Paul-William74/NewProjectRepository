package com.Ecommerce.demo.Model.WaitingList;


import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
//constraint needed to keep them from being added to the waiting list more than once for the same product
public final class WaitingList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Customer customer;

    //private Product or product size... no one really knows :|

    //if the customer has been notified if that product is back
    private boolean hasBeenNotified = false;

}

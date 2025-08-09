package com.Ecommerce.demo.Model.Invoice;


import com.Ecommerce.demo.Model.Cart.CartItem;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class InvoiceItem {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private CartItem cartItem;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Invoice invoice;
}

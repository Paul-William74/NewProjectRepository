package com.Ecommerce.demo.Model.Invoice;

import com.Ecommerce.demo.Model.User.Address;
import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public final class Invoice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length =20)
    private String invoiceNumber;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne (optional = false) @JoinColumn(nullable = false)
    private Address address;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<InvoiceItem> invoiceItems = new LinkedList<>();
}


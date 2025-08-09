package com.Ecommerce.demo.Model.Compare;


import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id" , "product_id"})
)
@Data
@NoArgsConstructor
public final class ProductsCompare {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToOne @JoinColumn(nullable = false)
    private Product product;
}

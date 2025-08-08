package com.Ecommerce.demo.Model.Review;

import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String questionAsked;

    @ManyToOne @JoinColumn(nullable = false)
    private Product product;

    @ManyToOne @JoinColumn(nullable = false)
    private Customer customer;

    @Column(nullable = false)
    private boolean hasBeenAnswered = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Question(String questionAsked, Product product, Customer customer) {
        this.questionAsked = questionAsked;
        this.product = product;
        this.customer = customer;
    }
}

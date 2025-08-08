package com.Ecommerce.demo.Model.Review;

import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "product_id"})
)
@NoArgsConstructor
public final class Review {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 300)
    private String text;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false, length = 35)
    private String header;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @JoinColumn(nullable = false) @ManyToOne(optional = false)
    private Customer customer;

    @JoinColumn(nullable = false) @ManyToOne(optional = false)
    private Product product;

    @OneToMany(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<LikeReview> reviewList = new ArrayList<>();

}

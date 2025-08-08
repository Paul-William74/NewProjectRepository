package com.Ecommerce.demo.Model.Review;
import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "review_id"})
)
@NoArgsConstructor
@Data
@AllArgsConstructor
public final class LikeReview {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LIKE_TYPE likeType;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Review review;
}

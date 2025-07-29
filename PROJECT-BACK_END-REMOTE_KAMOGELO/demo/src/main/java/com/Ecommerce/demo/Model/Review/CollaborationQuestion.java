package com.Ecommerce.demo.Model.Review;

import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"customer_id", "question_id"})
)
@Data
public final class CollaborationQuestion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToOne(cascade = CascadeType.ALL) @JoinColumn(nullable = false)
    private Question question;

    @CreationTimestamp
    private LocalDateTime joinedAt;


    public CollaborationQuestion(Question question, Customer customer) {
        this.question = question;
        this.customer = customer;
    }
}

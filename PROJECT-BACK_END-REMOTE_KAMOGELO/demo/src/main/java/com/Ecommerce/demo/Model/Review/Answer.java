package com.Ecommerce.demo.Model.Review;
import com.Ecommerce.demo.Model.Product.Product;
import com.Ecommerce.demo.Model.User.Admin;
import com.Ecommerce.demo.Model.User.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Answer {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String responseAnswer;

    @ManyToOne @JoinColumn(nullable = false)
    private Admin admin;

    @ManyToOne @JoinColumn(nullable = false)
    private Question question;

    @CreationTimestamp
    private LocalDateTime answeredAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public Answer(String responseAnswer, Admin admin, Question question) {
        this.responseAnswer = responseAnswer;
        this.admin = admin;
        this.question = question;
    }
}

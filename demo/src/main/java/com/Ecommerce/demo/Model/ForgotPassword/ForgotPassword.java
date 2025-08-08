package com.Ecommerce.demo.Model.ForgotPassword;

import com.Ecommerce.demo.Model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPassword {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne @JoinColumn(nullable = false, unique = true)
    private User user;

    @Column(nullable = false, length = 6)
    private String OTP;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public ForgotPassword(String OTP, User user) {
        this.OTP = OTP;
        this.user = user;
    }
}

package com.Ecommerce.demo.Model.Product;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "imgUrl"})
) //unique constraint to prevent duplicate images for the same product)
public final class ProductImage {

    @Id @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String imgUrl;

    @ManyToOne (optional = false) @JoinColumn(nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MATERIAL material;
}



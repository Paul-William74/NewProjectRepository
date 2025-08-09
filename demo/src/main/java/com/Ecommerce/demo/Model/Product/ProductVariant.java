package com.Ecommerce.demo.Model.Product;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_size_id", "material"}))
@Data
public final class ProductVariant {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private ProductSize productSize;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MATERIAL material;

    @Column(nullable = false)
    private Integer quantity;

    //this table basically says this size of this product with this material is available in this quantity

}

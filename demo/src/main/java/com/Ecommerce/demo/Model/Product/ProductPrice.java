package com.Ecommerce.demo.Model.Product;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"material", "product_id"})
)
public final class ProductPrice {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) @Enumerated(EnumType.STRING)
    private MATERIAL material;

    @Column(nullable = false)
    private Double basePrice;

    @JsonIgnore
    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Product product;

    @Column(nullable = false)
    private boolean onDiscount = false;

    @Column(nullable = false)
    private boolean isActive = false;

    @Column(nullable = false)
    private Double discountPercentage  =0.0;

    @Column(nullable = false)
    private Double discountAmount = 0.0;


    public ProductPrice(MATERIAL material, Double basePrice, Product product) {
        this.material = material;
        this.basePrice = basePrice;
        this.product = product;
    }
}

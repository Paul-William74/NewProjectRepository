package com.Ecommerce.demo.Model.Product.CustomJewellery;
import com.Ecommerce.demo.Model.Product.MATERIAL;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.util.unit.DataUnit;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public final class CustomProductMaterial {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MATERIAL materialChosen;

    @Column(nullable = false)
    private Double price;
}

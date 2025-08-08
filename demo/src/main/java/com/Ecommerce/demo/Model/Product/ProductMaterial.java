package com.Ecommerce.demo.Model.Product;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
    uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "material"})
)
public final class ProductMaterial {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //both the product and material are required to be unique

    @ManyToOne(optional = false) @JoinColumn(nullable = false)
    private Product product;

    @Enumerated(EnumType.STRING)
    private MATERIAL material;


    //to help make the materials of  that product have different images, I added
    // this field so it can be consistent with the template
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "productMaterial")
    public final List<ProductImage> imageList = new LinkedList<>();

}


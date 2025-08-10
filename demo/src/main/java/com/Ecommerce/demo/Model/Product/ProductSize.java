package com.Ecommerce.demo.Model.Product;

import com.Ecommerce.demo.Model.WaitingList.WaitingList;
import com.Ecommerce.demo.Model.Wishlist.WishList;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.engine.internal.Cascade;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "material"})
)
public final class ProductSize {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double size;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MATERIAL material;

    @Column(nullable = false)
    private Integer quantity;  // Stock quantity for this size + material

    @ManyToOne @JoinColumn(nullable = false)
    private Product product;

    @Column(nullable = false)
    private boolean onDiscount = false;


    @Column(nullable = false)
    private Double discountAmount = 0.0;

    @Column(nullable = false)
    private Double discountPercentage;


    @OneToMany(orphanRemoval = true, mappedBy = "productSize", cascade = CascadeType.ALL)
    private List<WaitingList> waitingListEntries = new LinkedList<>();


    @OneToMany(orphanRemoval = true, mappedBy = "productSize", cascade = CascadeType.ALL)
    private List<WishList> wishListsEntries = new LinkedList<>();

    public ProductSize(Double size, MATERIAL material, Integer quantity, Product product) {
        this.size = size;
        this.material = material;
        this.quantity = quantity;
        this.product = product;
    }
}


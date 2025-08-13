package com.Ecommerce.demo.Model.User;

import com.Ecommerce.demo.Model.Compare.ProductsCompare;
import com.Ecommerce.demo.Model.Review.LikeReview;
import com.Ecommerce.demo.Model.Review.Question;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public final class Customer extends User {

    //list of addresses for them to choose where the orders can go
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "user")
    private final List<Address> addressList = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private final List<LikeReview> likeReviews = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private final List<ProductsCompare> productsCompares = new LinkedList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "customer")
    private final List<Question> questions = new LinkedList<>();


    @Column(nullable = false)
    private int loyaltyPoints = 0;

    private String imgUrl;

    public void addProductCompare(ProductsCompare productsCompare) {
        productsCompare.setCustomer(this);
        this.getProductsCompares().add(productsCompare);
    }

    public void addAddress(Address address) {
        address.setUser(this);
        this.getAddressList().add(address);
    }

}

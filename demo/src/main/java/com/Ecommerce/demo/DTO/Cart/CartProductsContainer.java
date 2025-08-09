package com.Ecommerce.demo.DTO.Cart;



import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;


@Data
@RequiredArgsConstructor
public final class CartProductsContainer {

    private final int productCount; // Total number of products in the cart

    private final List<CartProduct> products; // List of CartProduct objects representing the products in the cart

    private final String totalPrice;
}

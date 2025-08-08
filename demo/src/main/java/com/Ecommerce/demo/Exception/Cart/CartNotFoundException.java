package com.Ecommerce.demo.Exception.Cart;

public final class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String message) {
        super(message);
    }
}

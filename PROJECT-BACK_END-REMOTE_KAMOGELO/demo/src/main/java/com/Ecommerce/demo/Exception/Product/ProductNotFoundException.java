package com.Ecommerce.demo.Exception.Product;

public final class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}

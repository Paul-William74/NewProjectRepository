package com.Ecommerce.demo.Exception.User;

public final class AdminNotFoundException extends RuntimeException {
    public AdminNotFoundException(String message) {
        super(message);
    }
}

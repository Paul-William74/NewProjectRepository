package com.Ecommerce.demo.Exception.User;

public final class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}

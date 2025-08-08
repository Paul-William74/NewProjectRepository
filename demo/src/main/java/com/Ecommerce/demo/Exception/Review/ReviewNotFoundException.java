package com.Ecommerce.demo.Exception.Review;

public final class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(String message) {
        super(message);
    }
}

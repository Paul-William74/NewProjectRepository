package com.Ecommerce.demo.Exception.Enum;

public final class GemStoneDoesNotExistException extends RuntimeException {
    public GemStoneDoesNotExistException(String message) {
        super(message);
    }
}

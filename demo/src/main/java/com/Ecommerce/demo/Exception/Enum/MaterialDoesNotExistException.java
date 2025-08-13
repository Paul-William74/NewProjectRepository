package com.Ecommerce.demo.Exception.Enum;

public final class MaterialDoesNotExistException extends RuntimeException {
    public MaterialDoesNotExistException(String message) {
        super(message);
    }
}

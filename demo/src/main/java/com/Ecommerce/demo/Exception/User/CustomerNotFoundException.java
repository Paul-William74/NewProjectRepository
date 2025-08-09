package com.Ecommerce.demo.Exception.User;

public final class CustomerNotFoundException extends RuntimeException {
  public CustomerNotFoundException(String message) {
    super(message);
  }
}

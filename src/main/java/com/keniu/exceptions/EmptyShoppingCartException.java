package com.keniu.exceptions;

public class EmptyShoppingCartException extends RuntimeException {

    public EmptyShoppingCartException(String message) {
        super(message);
    }
}

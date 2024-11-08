package com.keniu.exceptions;

/**
 * Exception thrown when a requested entity is not found in the application.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code EntityNotFoundException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}

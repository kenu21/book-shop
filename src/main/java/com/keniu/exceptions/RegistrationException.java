package com.keniu.exceptions;

/**
 * Exception thrown when an error occurs during the user registration process.
 */
public class RegistrationException extends RuntimeException {

    /**
     * Constructs a new {@code RegistrationException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public RegistrationException(String message) {
        super(message);
    }
}

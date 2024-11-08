package com.keniu.exceptions;

/**
 * Exception thrown when an error occurs during data processing operations,
 * such as database access or data manipulation.
 */
public class DataProcessingException extends RuntimeException {

    /**
     * Constructs a new {@code DataProcessingException} with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public DataProcessingException(String message) {
        super(message);
    }
}

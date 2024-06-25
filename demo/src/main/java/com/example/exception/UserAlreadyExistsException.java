package com.example.exception;

/**
 * Exception thrown when attempting to add a user that already exists.
 */
public class UserAlreadyExistsException extends Exception {

    /**
     * Constructs a new UserAlreadyExistsException with the specified detail message.
     *
     * @param message The detail message.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new UserAlreadyExistsException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause of the exception.
     */
    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

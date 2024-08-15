package org.example.prispevky.exception;

/**
 * Custom exception to be thrown when a user is not found.
 *
 * This exception is a subclass of {@link RuntimeException} and is used to signal
 * that a user with a specified ID could not be found in the system. It extends
 * {@link RuntimeException} to provide a custom message for error handling purposes.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code UserNotFoundException} with the specified detail message.
     *
     * @param message the detail message to be used in the exception. It provides
     *                more information about the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}

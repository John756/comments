package org.example.prispevky.exception;

/**
 * Custom exception to be thrown when a comment with a specific ID is not found.
 *
 * This exception extends {@link RuntimeException} and is intended to be used when
 * a requested comment cannot be found in the system based on its ID. It allows for
 * providing a custom message to give more context about the missing comment.
 */
public class CommentIdNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code CommentIdNotFoundException} with the specified detail message.
     *
     * @param message the detail message that explains the reason for the exception.
     *                This message is passed to the {@link RuntimeException} constructor
     *                and can be used to provide additional information about the missing
     *                comment.
     */
    public CommentIdNotFoundException(String message) {
        super(message);
    }
}

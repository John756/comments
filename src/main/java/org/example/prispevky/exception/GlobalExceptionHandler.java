package org.example.prispevky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling various types of exceptions across the application.
 *
 * This class is annotated with {@link ControllerAdvice} to handle exceptions globally
 * for all controllers in the application. It provides custom responses for specific
 * exceptions and handles validation errors and general exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Handles {@link CommentIdNotFoundException} by returning a 404 Not Found response.
     *
     * @param ex the exception that was thrown.
     * @return a {@link ResponseEntity} with a map containing the error message and a 404 status.
     */
    @ExceptionHandler({CommentIdNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleCommentIdNotFoundException(CommentIdNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link UserNotFoundException} by returning a 404 Not Found response.
     *
     * @param ex the exception that was thrown.
     * @return a {@link ResponseEntity} with a map containing the error message and a 404 status.
     */
    @ExceptionHandler({UserNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles {@link MethodArgumentNotValidException} for validation errors in request data.
     *
     * @param ex the exception that was thrown.
     * @return a {@link ResponseEntity} with a map of validation errors and a 400 Bad Request status.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles general exceptions by returning a 500 Internal Server Error response.
     *
     * @param ex the exception that was thrown.
     * @return a {@link ResponseEntity} with a map containing the error message and a 500 status.
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

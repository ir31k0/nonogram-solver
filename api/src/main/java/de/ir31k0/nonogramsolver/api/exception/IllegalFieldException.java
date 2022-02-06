package de.ir31k0.nonogramsolver.api.exception;

/**
 * This exception is thrown when a field conflict occurs.
 */
public class IllegalFieldException extends RuntimeException {
    public IllegalFieldException(String message) {
        super(message);
    }
}

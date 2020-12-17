package de.ir31k0.nonogramsolver.exception;

/**
 * This exception is thrown when a field conflict occurs.
 */
public class IllegalFieldException extends Exception {
    public IllegalFieldException(String message) {
        super(message);
    }
}

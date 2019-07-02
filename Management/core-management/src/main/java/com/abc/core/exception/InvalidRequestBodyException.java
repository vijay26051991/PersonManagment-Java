package com.abc.core.exception;

/**
 * Exception class for request body validation
 *
 *  @author vijaykumar.s 07/02/2019
 */
public class InvalidRequestBodyException extends RuntimeException {

    private static final long serialVersionUID = 693797988111878890L;

    public InvalidRequestBodyException(final String message) {
        super(message);
    }

    public InvalidRequestBodyException(final String message, final Throwable cause) {
        super(message, cause);
    }
}

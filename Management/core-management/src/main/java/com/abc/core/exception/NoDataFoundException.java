package com.abc.core.exception;

public class NoDataFoundException extends Exception {
    public NoDataFoundException(final String message) {
        super(message);
    }

    public NoDataFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

}

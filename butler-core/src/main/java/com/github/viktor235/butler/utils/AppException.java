package com.github.viktor235.butler.utils;

public class AppException extends Exception {
    public AppException(Throwable cause) {
        super(cause);
    }

    public AppException(String message) {
        super(message);
    }

    public AppException(String message, Throwable cause) {
        super(message, cause);
    }
}

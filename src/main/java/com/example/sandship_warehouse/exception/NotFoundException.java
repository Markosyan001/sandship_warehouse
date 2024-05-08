package com.example.sandship_warehouse.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {

    private String errorMessageCode;
    private final int errorCode = 404;

    public NotFoundException() {
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, String errorMessageCode) {
        super(message);
        this.errorMessageCode = errorMessageCode;
    }

    public NotFoundException(String message, Object ... args) {
        super(String.format(message, args));
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(Throwable cause) {
        super(cause);
    }

    public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

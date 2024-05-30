package com.managechurch.services.exceptions;

public class DataItegrityException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DataItegrityException(String message) {
        super(message);
    }

}

package com.my.insurance.exception;

public class CustomExceptionHandler extends RuntimeException {
    public CustomExceptionHandler(String message) {
        super(message);
    }
}

package com.my.insurance.exception;

import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomExceptionHandler.class)
    public String handleCustom(CustomExceptionHandler ex) {
        return ex.getMessage();
    }
}
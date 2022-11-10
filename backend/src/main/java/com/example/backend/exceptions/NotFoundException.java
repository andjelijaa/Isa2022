package com.example.backend.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException(Class className, String fieldName, Object fieldValue) {
        super(String.format("%s with %s = %s does not exist", className.getSimpleName(), fieldName, fieldValue));
    }

    public NotFoundException() {
        super("This resource cannot be found");
    }

    public NotFoundException(String message) {
        super(message);
    }
}
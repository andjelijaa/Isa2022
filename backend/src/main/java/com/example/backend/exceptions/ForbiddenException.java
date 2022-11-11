package com.example.backend.exceptions;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException() {
        super("This resource is forbidden");
    }
}
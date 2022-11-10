package com.example.backend.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException() {
        super("You do not have authorization to access this resource");
    }
}
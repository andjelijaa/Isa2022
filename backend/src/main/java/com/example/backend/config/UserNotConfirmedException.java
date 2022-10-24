package com.example.backend.config;

public class UserNotConfirmedException extends RuntimeException {
    public UserNotConfirmedException(String email) {
        super("user not confirmed: " + email);
    }
}
package com.example.backend.config;

public class UserNotConfirmedException extends RuntimeException {
    public UserNotConfirmedException(String username) {
        super("user not confirmed: " + username);
    }
}
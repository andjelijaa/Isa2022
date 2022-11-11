package com.example.backend.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class ErrorResponse {
    @Getter
    String message;
    @Getter
    String status;
    @Getter
    Map<String, String> details = new HashMap<>();

    public ErrorResponse(HttpStatus status, String message) {
        this.status = status.name();
        this.message = message;
    }

    public void putDetail(String key, String value) {
        this.details.put(key, value);
    }
}
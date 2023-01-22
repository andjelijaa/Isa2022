package com.example.backend.models.request;

import lombok.Data;

@Data
public class CreateQuestionDto {
    private String text;
    private String answer;
}

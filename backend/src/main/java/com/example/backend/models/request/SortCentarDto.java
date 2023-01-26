package com.example.backend.models.request;

import lombok.Data;

@Data
public class SortCentarDto {
    private boolean grad;
    private boolean ocena;

    public SortCentarDto(boolean grad, boolean ocena) {
        this.grad = grad;
        this.ocena = ocena;
    }
}
package com.example.backend.models.request;

import lombok.Data;

@Data
public class SortCentarDto {
    private boolean grad;
    private boolean ocena;
    private boolean naziv;

    public SortCentarDto(boolean grad, boolean ocena, boolean naziv) {
        this.grad = grad;
        this.ocena = ocena;
        this.naziv = naziv;
    }
}
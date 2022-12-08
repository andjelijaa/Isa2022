package com.example.backend.models.request;

import lombok.Data;

@Data
public class SortZakazanePoseteDto {
    private boolean trajanje;
    private boolean ocena;
    private boolean datum;
}
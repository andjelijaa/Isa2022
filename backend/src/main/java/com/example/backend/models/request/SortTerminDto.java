package com.example.backend.models.request;

import com.example.backend.models.enums.StatusTermina;
import lombok.Data;

@Data
public class SortTerminDto {
    private boolean datum;
    private StatusTermina status;
}

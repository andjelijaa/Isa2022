package com.example.backend.models.request;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CreateTerminDto {
    private Timestamp datum;
    private long pacijentId;
    private long doktorId;
    private long centarId;
}
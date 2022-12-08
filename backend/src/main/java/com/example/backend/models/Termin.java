package com.example.backend.models;

import com.example.backend.models.enums.StatusTermina;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "termin")
@Data
public class Termin {
    private Long id;
    private Timestamp datum;
    private StatusTermina status;
    private long pacijentId;
    private long doktorId;
    private long centarId;
}
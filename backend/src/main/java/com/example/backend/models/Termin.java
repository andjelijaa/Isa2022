package com.example.backend.models;

import com.example.backend.models.enums.StatusTermina;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "termin")
@Data
public class Termin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp datum;
    private StatusTermina status;
    private long pacijentId;
    private long doktorId;
    private long centarId;
}
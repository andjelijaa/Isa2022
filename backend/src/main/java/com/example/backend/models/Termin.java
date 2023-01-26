package com.example.backend.models;

import com.example.backend.models.enums.StatusTermina;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private boolean zakazan;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "pacijent_id", nullable = true)
    private User pacijent;

    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "centar_id", nullable = true)
    private Centar centar;
}
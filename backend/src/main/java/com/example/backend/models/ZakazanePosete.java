package com.example.backend.models;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "zakazane_posete")
@Data
public class ZakazanePosete {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp termin;
    private Centar centar;
    private User davaoc;
}
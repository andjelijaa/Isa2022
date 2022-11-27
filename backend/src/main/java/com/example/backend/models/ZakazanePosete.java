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

    @ManyToOne
    @JoinColumn(name = "centar_id", nullable = true)
    private Centar centar;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "davalac_id", referencedColumnName = "id")
    private User davaoc;
}
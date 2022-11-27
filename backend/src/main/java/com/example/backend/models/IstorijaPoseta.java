package com.example.backend.models;

import com.example.backend.service.UserService;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "istorija_poseta")
@Data
public class IstorijaPoseta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp datumPosete;

    @ManyToOne
    @JoinColumn(name = "centar_id", nullable = true)
    private Centar centar;

    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "davalac_id", nullable = true)
    private User davalac;
}
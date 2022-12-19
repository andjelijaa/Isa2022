package com.example.backend.models;

import com.example.backend.models.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String password;
    private String email;
    private String ime;
    private String prezime;
    private String grad;
    private String adresa;
    private String drzava;
    private String phone;
    private String jmbg;
    private Genter pol;
    private Role role;
    private String activation;
    private String zanimanje;
    private String ustanova;
    private int penali;

    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "centar_id", nullable = true)
    private Centar centar;

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "davalac")
    private List<IstorijaPoseta> posete;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pacijent")
    private  List<Termin> zakazaniTerminiPacijent;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "doktor")
    private List<Termin> zakazaniTerminiDoktor;


}

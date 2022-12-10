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
    private String JMBG;
    private Genter genter;
    private Role role;
    private String activation;
    private String ustanova;
    private int penali;

    @ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "centar_id", nullable = true)
    private Centar centar;

    @OneToMany(fetch = FetchType.LAZY,cascade=CascadeType.ALL,mappedBy = "davalac")
    private List<IstorijaPoseta> posete;

    @OneToOne(mappedBy = "davaoc")
    private ZakazanePosete zakazanaPoseta;

}

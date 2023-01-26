package com.example.backend.models;

import com.example.backend.models.enums.Genter;
import com.example.backend.models.enums.Role;
import com.example.backend.models.request.CreateQuestionDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private boolean questionFlag;

    @Transient
    private List<CreateQuestionDto> questions;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "centar_id", nullable = true)
    private Centar centar;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "pacijent")
    private  List<Termin> zakazaniTerminiPacijent;

}

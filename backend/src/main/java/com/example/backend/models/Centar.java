package com.example.backend.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "centri")
public class Centar {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String grad;
    private String adresa;
    private String drzava;
    private String phone;
    private float ocena;


//    @JsonIgnore
    @OneToMany(mappedBy = "centar")
    private List<User> zaposleni;

//    @OneToMany(mappedBy = "centar")
//    private List<IstorijaPoseta> istorijaPoseta;

    @JsonIgnore
    @OneToMany(mappedBy = "centar")
    private List<Termin> termini;

}
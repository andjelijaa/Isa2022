package com.example.backend.models;

import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String address;
    private String ime;
    private String prezime;
    private String grad;
    private String drzava;
    private String phone;

    private Role role;
    private String activation;



}

package com.example.backend.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "zalba")
@Data
public class Zalba {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String tekst;
    private String userEmail;
}
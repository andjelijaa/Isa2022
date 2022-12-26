package com.example.backend.models.response;


import com.example.backend.models.User;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CentarDto {
    private Long id;
    private String grad;
    private String adresa;
    private String drzava;
    private String phone;
    private float ocena;

    public CentarDto(Long id,
                     String grad,
                     String adresa,
                     String drzava,
                     String phone,
                     float ocena) {
        this.id = id;
        this.grad = grad;
        this.adresa = adresa;
        this.drzava = drzava;
        this.phone = phone;
        this.ocena = ocena;
    }
}
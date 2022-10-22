package com.example.backend.models.request;


import com.example.backend.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {

    private Long id;
    private String username;
    private String ime;
    private String prezime;

    private String password;
    private String adresa;
    private String grad;
    private String drzava;
    private String phone;

    private Role role;

}


package com.example.backend.models.request;


import com.example.backend.models.Genter;
import com.example.backend.models.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {

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

}


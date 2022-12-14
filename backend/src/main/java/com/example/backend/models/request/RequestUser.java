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

    private String password;
    private String email;


}


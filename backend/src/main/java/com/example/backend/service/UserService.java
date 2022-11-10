package com.example.backend.service;

import com.example.backend.models.User;

import java.security.Principal;

public interface UserService{
    User checkActivationCode(String code);
    User findByEmail(String Email);
    void save(User user);
    User getActivatedUserFromPrincipal(Principal principal)throws Exception;
}
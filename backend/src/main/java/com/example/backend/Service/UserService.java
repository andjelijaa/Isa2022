package com.example.backend.Service;

import com.example.backend.models.User;

public interface UserService{
    User checkActivationCode(String code);
    User findByUsername(String username);
    void save(User user);
}
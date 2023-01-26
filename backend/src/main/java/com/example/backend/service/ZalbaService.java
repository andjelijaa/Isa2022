package com.example.backend.service;

import com.example.backend.models.User;
import com.example.backend.models.Zalba;

import java.util.List;

public interface ZalbaService {
    List<Zalba> findAll(User user);

    void save(Zalba zalba);
}

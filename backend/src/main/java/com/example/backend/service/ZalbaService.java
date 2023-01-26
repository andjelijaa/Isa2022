package com.example.backend.service;

import com.example.backend.models.Zalba;

import java.util.List;

public interface ZalbaService {
    List<Zalba> findAll();

    void save(Zalba zalba);
}

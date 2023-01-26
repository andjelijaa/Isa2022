package com.example.backend.service;

import com.example.backend.models.Termin;
import com.example.backend.models.User;

import java.util.List;

public interface TerminService {

    Termin findById(Long id) throws Exception;

    List<Termin> istorijaPoseta(User user);

    boolean zakaziTermin(User user, Long terminId);

    boolean otkaziTermin(User user, Long terminId);

    List<Termin> getZakazanePosete(User user);
}

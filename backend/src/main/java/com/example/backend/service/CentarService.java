package com.example.backend.service;

import com.example.backend.models.*;
import com.example.backend.models.request.*;
import com.example.backend.models.response.CentarDto;

import java.util.List;

public interface CentarService {

    List<CentarDto> getAllCentri(SortCentarDto sortCentarDto);

    CentarDto getCentarById(Long centarId) throws Exception;

    List<IstorijaPoseta> getIstorijuPosetaZaKorisnikaUCentru(User user, Long centarId);

    void kreirajTermin(Long centarId, String datum, String vreme);

    List<Termin> getZakazanePosete(Long centarId, SortZakazanePoseteDto sortZakazanePoseteDto);

    List<Termin> getKreiraniTermini(Long centarId, SortTerminDto sortTerminDto);

    void createTermin(User user, Long centarId, CreateTerminDto createTerminDto);

    int getPenali(User user, Long centarId);

    List<Termin> getSlobodniTermini(Long centarId);


}

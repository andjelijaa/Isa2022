package com.example.backend.service;

import com.example.backend.models.IstorijaPoseta;
import com.example.backend.models.Termin;
import com.example.backend.models.User;
import com.example.backend.models.ZakazanePosete;
import com.example.backend.models.request.SortCentarDto;
import com.example.backend.models.request.SortTerminDto;
import com.example.backend.models.request.SortZakazanePoseteDto;
import com.example.backend.models.response.CentarDto;

import java.util.List;

public interface CentarService {

    List<CentarDto> getAllCentri(SortCentarDto sortCentarDto);

    CentarDto getCentarById(Long centarId) throws Exception;

    List<IstorijaPoseta> getIstorijuPosetaZaKorisnikaUCentru(User user, Long centarId);

    void kreirajTermin(Long centarId, String datum, String vreme);
    List<ZakazanePosete> getZakazanePosete(Long centarId, SortZakazanePoseteDto sortZakazanePoseteDto);

    List<Termin> getKreiraniTermini(Long centarId, SortTerminDto sortTerminDto);
}

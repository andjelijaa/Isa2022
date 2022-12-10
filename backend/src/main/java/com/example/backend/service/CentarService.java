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

    List<ZakazanePosete> getZakazanePosete(Long centarId, SortZakazanePoseteDto sortZakazanePoseteDto);

    List<Termin> getKreiraniTermini(Long centarId, SortTerminDto sortTerminDto);

    void zakaziTermin(User user, Long centarId, CreateTerminDto createTerminDto);

    List<QRCode> getLstQrCodesWithSortByDatumIzdavanjaAndStatus(User user, Long centarId, SortQrCodeDto sortQrCodeDto);

    int getPenali(User user, Long centarId);


}

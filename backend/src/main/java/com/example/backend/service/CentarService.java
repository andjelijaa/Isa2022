package com.example.backend.service;

import com.example.backend.models.request.SortCentarDto;
import com.example.backend.models.response.CentarDto;

import java.util.List;

public interface CentarService {

    List<CentarDto> getAllCentri(SortCentarDto sortCentarDto);

    CentarDto getCentarById(Long centarId) throws Exception;

    void kreirajTermin(Long centarId, String datum, String vreme);
}

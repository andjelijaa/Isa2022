package com.example.backend.service;

import com.example.backend.models.response.CentarDto;
import java.util.List;
public interface CentarService {

    List<CentarDto> getAllCentri();
    CentarDto getCentarById(Long centarId) throws Exception;
}

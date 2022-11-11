package com.example.backend.service.Impl;

import com.example.backend.exceptions.NotFoundException;
import com.example.backend.models.Centar;
import com.example.backend.models.response.CentarDto;
import com.example.backend.repository.CentarRepository;
import com.example.backend.service.CentarService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentarServiceImpl implements CentarService {


    private final CentarRepository centarRepository;


    public CentarServiceImpl(CentarRepository centarRepository) {
        this.centarRepository = centarRepository;
    }

    public List<CentarDto> getAllCentri() {

        List<Centar> centri = centarRepository.findAll();

        List<CentarDto> centriDto = centri
                .stream()
                .map(centar -> new CentarDto(
                        centar.getId(),
                        centar.getGrad(),
                        centar.getAdresa(),
                        centar.getDrzava(),
                        centar.getPhone(),
                        centar.getOcena(),
                        centar.getZaposleni()
                ))
                .collect(Collectors.toList());

        return centriDto;
    }

    public CentarDto getCentarById(Long centarId) throws Exception {

        Centar centar = centarRepository.findById(centarId)
                .orElseThrow(() -> new NotFoundException(Centar.class, "id", centarId));

        return new CentarDto(
                centar.getId(),
                centar.getGrad(),
                centar.getAdresa(),
                centar.getDrzava(),
                centar.getPhone(),
                centar.getOcena(),
                centar.getZaposleni()
        );
    }
}

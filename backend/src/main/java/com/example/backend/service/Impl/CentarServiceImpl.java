package com.example.backend.service.Impl;

import com.example.backend.exceptions.NotFoundException;
import com.example.backend.exceptions.UnauthorizedException;
import com.example.backend.models.*;
import com.example.backend.models.enums.Role;
import com.example.backend.models.enums.StatusTermina;
import com.example.backend.models.request.*;
import com.example.backend.models.response.CentarDto;
import com.example.backend.repository.CentarRepository;
import com.example.backend.repository.TerminRepository;
import com.example.backend.service.CentarService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentarServiceImpl implements CentarService {


    private final CentarRepository centarRepository;
    private final TerminRepository terminRepository;

    public CentarServiceImpl(CentarRepository centarRepository,
                             TerminRepository terminrepository) {
        this.centarRepository = centarRepository;
        this.terminRepository = terminrepository;
    }

    @Override
    public List<CentarDto> getAllCentri(SortCentarDto sortCentarDto) {

        List<Centar> centri;

        if (sortCentarDto.isGrad()) {
            centri = centarRepository.findAll(Sort.by(Sort.Direction.ASC, "grad"));
        } else if (sortCentarDto.isNaziv()) {
            centri = centarRepository.findAll(Sort.by(Sort.Direction.ASC, "naziv"));
        } else {
            centri = centarRepository.findAll(Sort.by(Sort.Direction.ASC, "ocena"));
        }

        List<CentarDto> dto = centri
                .stream()
                .map(centar -> new CentarDto(
                        centar.getId(),
                        centar.getGrad(),
                        centar.getAdresa(),
                        centar.getDrzava(),
                        centar.getPhone(),
                        centar.getOcena()
                ))
                .collect(Collectors.toList());
        return dto;
    }

    @Override
    public CentarDto getCentarById(Long centarId) throws Exception {

        Centar centar = centarRepository.findById(centarId)
                .orElseThrow(() -> new NotFoundException(Centar.class, "id", centarId));

        return new CentarDto(
                centar.getId(),
                centar.getGrad(),
                centar.getAdresa(),
                centar.getDrzava(),
                centar.getPhone(),
                centar.getOcena()
        );
    }

    @Override
    public List<Termin> getZakazanePosete(Long centarId, SortZakazanePoseteDto sortZakazanePoseteDto) {

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        List<Termin> zPosete;
        if (sortZakazanePoseteDto.isDatum()) {
            zPosete = terminRepository.findAll(Sort.by(Sort.Direction.ASC, "datum"));
        } else{
            zPosete = terminRepository.findAll(Sort.by(Sort.Direction.DESC, "datum"));
        }
        return zPosete
                .stream()
                .filter(termin -> termin.getDatum().after(now))
                .filter(Termin::isZakazan)
                .collect(Collectors.toList());
    }

    @Override
    public void kreirajTermin(Long centarId, String datum, String vreme) {

        Timestamp termin = Timestamp.valueOf(datum + " " + vreme);

        Centar centar = centarRepository.findById(centarId)
                .orElseThrow(() -> new NotFoundException(Centar.class, "id", centarId));
        Termin termin1 = new Termin();
        termin1.setCentar(centar);
        termin1.setDatum(termin);
        termin1.setStatus(StatusTermina.NOV);

        terminRepository.save(termin1);
    }


    @Override
    public List<Termin> getKreiraniTermini(Long centarId, SortTerminDto sortTerminDto) {

        List<Termin> termini;
        if (sortTerminDto.isDatum()) {
            termini = terminRepository.findAll(Sort.by(Sort.Direction.ASC, "datum"));
        } else {
            termini = terminRepository.findAll();
        }

        switch (sortTerminDto.getStatus()){
            case NOV:
                return termini
                        .stream()
                        .filter(termin -> termin.getStatus().equals(StatusTermina.NOV))
                        .collect(Collectors.toList());
            case OBRAĐEN:
                return termini
                        .stream()
                        .filter(termin -> termin.getStatus().equals(StatusTermina.OBRAĐEN))
                        .collect(Collectors.toList());
            case ODBIJEN:
                return termini
                        .stream()
                        .filter(termin -> termin.getStatus().equals(StatusTermina.ODBIJEN))
                        .collect(Collectors.toList());
            default:
                return termini;

        }
    }

    @Override
    public void createTermin(User user, Long centarId, CreateTerminDto createTerminDto) {
//        if admin
        if (user.getRole().equals(Role.ROLE_ADMINISTRATOR)) {
            Centar centar = centarRepository.findById(centarId)
                    .orElseThrow(() -> new NotFoundException(Centar.class, "id", centarId));
            Termin termin = new Termin();
            termin.setCentar(centar);
            termin.setDatum(createTerminDto.getDatum());
            termin.setStatus(StatusTermina.NOV);
            termin.setZakazan(false);
            terminRepository.save(termin);
        }else {
            throw new UnauthorizedException();
        }
    }

    @Override
    public int getPenali(User user, Long centarId) {
        return user.getPenali();
    }

    @Override
    public List<Termin> getSlobodniTermini(Long centarId) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        List<Termin> termini = terminRepository.findAll();
        List<Termin> rester = termini
                .stream()
                .filter(termin -> termin.getDatum().after(now))
                .filter(termin -> termin.getStatus().equals(StatusTermina.NOV))
                .filter(termin -> termin.isZakazan() == false)
                .collect(Collectors.toList());
        return rester;
    }

    @Override
    public List<Centar> getCentriZaZalbe(User user) {
        List<Termin> termini = terminRepository.findByPacijentId(user.getId());
        return termini
                .stream()
                .filter(termin -> termin.getDatum().before(Timestamp.valueOf(LocalDateTime.now())))
                .map(termin -> termin.getCentar())
                .collect(Collectors.toList());
    }
}

package com.example.backend.service.Impl;

import com.example.backend.exceptions.NotFoundException;
import com.example.backend.exceptions.UnauthorizedException;
import com.example.backend.models.*;
import com.example.backend.models.enums.Role;
import com.example.backend.models.enums.StatusTermina;
import com.example.backend.models.request.*;
import com.example.backend.models.response.CentarDto;
import com.example.backend.repository.CentarRepository;
import com.example.backend.repository.IstorijaPosetaRepository;
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
    private final IstorijaPosetaRepository istorijaPosetaRepository;
    private final TerminRepository terminRepository;

    public CentarServiceImpl(CentarRepository centarRepository,
                             IstorijaPosetaRepository istorijaPosetaRepository,
                             TerminRepository terminrepository) {
        this.centarRepository = centarRepository;
        this.istorijaPosetaRepository = istorijaPosetaRepository;
        this.terminRepository = terminrepository;
    }

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


    public List<IstorijaPoseta> getIstorijuPosetaZaKorisnikaUCentru(User user, Long centarId) {

        return istorijaPosetaRepository.findAllByDavalacIdAndCentarId(user.getId(), centarId);
    }

    public List<Termin> getZakazanePosete(Long centarId, SortZakazanePoseteDto sortZakazanePoseteDto) {

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        List<Termin> zPosete;
        if (sortZakazanePoseteDto.isDatum()) {
            zPosete = terminRepository.findAll(Sort.by(Sort.Direction.ASC, "datum"));
        } else if (sortZakazanePoseteDto.isOcena()) {
            zPosete = terminRepository.findAll(Sort.by(Sort.Direction.ASC, "ocena"));
        } else {
            zPosete = terminRepository.findAll(Sort.by(Sort.Direction.ASC, "trajanje"));
        }
        return zPosete
                .stream()
                .filter(termin -> termin.getDatum().after(now))
                .filter(Termin::isZakazan)
                .collect(Collectors.toList());
    }

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
            case OBRA??EN:
                return termini
                        .stream()
                        .filter(termin -> termin.getStatus().equals(StatusTermina.OBRA??EN))
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

    public int getPenali(User user, Long centarId) {
        return user.getPenali();
    }

    public List<Termin> getSlobodniTermini(Long centarId) {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        List<Termin> termini = terminRepository.findAll();
        return termini
                .stream()
                .filter(termin -> termin.getDatum().after(now))
                .filter(termin -> termin.getStatus().equals(StatusTermina.NOV))
                .filter(termin -> termin.isZakazan() == false)
                .collect(Collectors.toList());
    }
}

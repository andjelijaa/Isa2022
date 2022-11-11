package com.example.backend.service.Impl;

import com.example.backend.exceptions.NotFoundException;
import com.example.backend.models.Centar;
import com.example.backend.models.IstorijaPoseta;
import com.example.backend.models.User;
import com.example.backend.models.ZakazanePosete;
import com.example.backend.models.response.CentarDto;
import com.example.backend.repository.CentarRepository;
import com.example.backend.repository.IstorijaPosetaRepository;
import com.example.backend.repository.ZakazanePoseteRepository;
import com.example.backend.service.CentarService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CentarServiceImpl implements CentarService {


    private final CentarRepository centarRepository;
    private final IstorijaPosetaRepository istorijaPosetaRepository;
    private final ZakazanePoseteRepository zakazanePoseteRepository;

    public CentarServiceImpl(CentarRepository centarRepository, IstorijaPosetaRepository istorijaPosetaRepository, ZakazanePoseteRepository zakazanePoseteRepository) {
        this.centarRepository = centarRepository;
        this.istorijaPosetaRepository = istorijaPosetaRepository;
        this.zakazanePoseteRepository = zakazanePoseteRepository;
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


    public List<IstorijaPoseta> getIstorijuPosetaZaKorisnikaUCentru(User user, Long centarId) {

        List<IstorijaPoseta> ip = istorijaPosetaRepository.findAllByDavalacIdAndCentarId(user.getId(), centarId);

        return ip;
    }

    public List<ZakazanePosete> getZakazanePosete(Long centarId) {

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        List<ZakazanePosete> zPosete = zakazanePoseteRepository.findAllByCentarId(centarId);

        return zPosete
                .stream()
                .filter(poseta -> poseta.getTermin().after(now))
                .collect(Collectors.toList());
    }
}

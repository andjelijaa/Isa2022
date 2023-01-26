package com.example.backend.service.Impl;

import com.example.backend.exceptions.NotFoundException;
import com.example.backend.models.Termin;
import com.example.backend.models.User;
import com.example.backend.models.enums.StatusTermina;
import com.example.backend.repository.TerminRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.TerminService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TerminServiceImpl implements TerminService {

    private final TerminRepository terminRepository;
    private final UserRepository userRepository;

    public TerminServiceImpl(TerminRepository terminRepository, UserRepository userRepository) {
        this.terminRepository = terminRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Termin findById(Long id) throws Exception {
        return terminRepository.findById(id).orElseThrow(() -> new NotFoundException("Termin ne postoji"));
    }

    @Override
    public List<Termin> istorijaPoseta(User user) {

        List<Termin> termini = terminRepository.findByPacijentId(user.getId());
        return termini
                .stream()
                .filter(termin -> termin.getStatus().equals(StatusTermina.OBRAĐEN))
                .filter(termin -> termin.getDatum().before(Timestamp.valueOf(LocalDateTime.now())))
                .collect(Collectors.toList());
    }

    @Override
    public boolean zakaziTermin(User user, Long terminId) {
        Termin termin = terminRepository.findById(terminId).orElseThrow(() -> new NotFoundException("Termin ne postoji"));
        Optional<Termin> zadnjiTerminKorisnika = terminRepository.findByPacijentIdOrderByDatumDesc(user.getId());
        boolean flag = false;
        if(zadnjiTerminKorisnika.isPresent()){
            if (zadnjiTerminKorisnika.get().getDatum().before(Timestamp.valueOf(LocalDateTime.now().minusMonths(6))) && zadnjiTerminKorisnika.get().getStatus().equals(StatusTermina.OBRAĐEN) && user.getPenali() < 3){
                flag = true;
            }
        }
        if(!user.getQuestions().isEmpty() && flag) {
            termin.setPacijent(user);
            termin.setZakazan(true);
            terminRepository.save(termin);
            return true;
        }
        return false;
    }

    @Override
    public boolean otkaziTermin(User user, Long terminId) {
        Termin termin = terminRepository.findById(terminId).orElseThrow(() -> new NotFoundException("Termin ne postoji"));
        if(termin.getDatum().after(Timestamp.valueOf(LocalDateTime.now().plusDays(1)))){

            user.setPenali(user.getPenali() + 1);
            userRepository.save(user);
        }
        termin.setPacijent(null);
        termin.setZakazan(false);
        termin.setStatus(StatusTermina.NOV);
        terminRepository.save(termin);
        return true;
    }

    @Override
    public List<Termin> getZakazanePosete(User user) {
        List<Termin> termini = terminRepository.findByPacijentId(user.getId());
        return termini
                .stream()
                .filter(Termin::isZakazan)
                .filter(termin -> termin.getStatus().equals(StatusTermina.OBRAĐEN))
                .filter(termin -> termin.getDatum().after(Timestamp.valueOf(LocalDateTime.now())))
                .collect(Collectors.toList());
    }
}

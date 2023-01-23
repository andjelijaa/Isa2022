package com.example.backend.controller;


import com.example.backend.models.*;
import com.example.backend.models.enums.Genter;
import com.example.backend.models.enums.Role;
import com.example.backend.repository.CentarRepository;
import com.example.backend.repository.IstorijaPosetaRepository;
import com.example.backend.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class DatabaseController {
    private final CentarRepository centarRepository;
    private final IstorijaPosetaRepository istorijaPosetaRepository;
    private final UserRepository userRepository;

    public DatabaseController(CentarRepository centarRepository,
                              IstorijaPosetaRepository istorijaPosetaRepository,
                              UserRepository userRepository) {
        this.centarRepository = centarRepository;
        this.istorijaPosetaRepository = istorijaPosetaRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/init")
    public String initDB(){
        User user = new User();
        user.setActivation(null);
        user.setEmail("test@test.com");
        user.setAdresa("adresa testa 1");
        user.setRole(Role.ROLE_KORISNIK);
        user.setPassword("password");
        user.setIme("test1");
        user.setPhone("1234567890");
        user.setGrad("grad");
        user.setDrzava("drzava");
        user.setPol(Genter.MALE);
        user.setPrezime("prezime");
        user.setJmbg("1223443321345");

        Centar centar = new Centar();
        centar.setAdresa("adresa testa 2");
        centar.setGrad("grad");
        centar.setDrzava("drzava");
        centar.setPhone("1234567890");
        centar.setOcena(0);
        centarRepository.saveAndFlush(centar);

        user.setCentar(centar);
        userRepository.saveAndFlush(user);

        IstorijaPoseta istorijaPoseta1 = new IstorijaPoseta();
        IstorijaPoseta istorijaPoseta2 = new IstorijaPoseta();
        istorijaPoseta1.setCentar(centar);
        istorijaPoseta1.setDavalac(user);
        istorijaPoseta1.setDatumPosete(Timestamp.valueOf(LocalDateTime.now().minusDays(3)));

        istorijaPoseta2.setCentar(centar);
        istorijaPoseta2.setDavalac(user);
        istorijaPoseta2.setDatumPosete(Timestamp.valueOf(LocalDateTime.now().minusDays(4)));

        istorijaPosetaRepository.saveAndFlush(istorijaPoseta1);
        istorijaPosetaRepository.saveAndFlush(istorijaPoseta2);

        return "Napunjeno";
    }

    @GetMapping("/get-all-database")
    public Map<String, Object> getAllDatabase(){
        List<Centar> centri = centarRepository.findAll();
        List<User> useri = userRepository.findAll();
        List<IstorijaPoseta> istorijePoseta = istorijaPosetaRepository.findAll();

        Map<String, Object> map = new HashMap<>();
        map.put("Centri", centri);
        map.put("Useri", useri);
        map.put("IstorijaPoseta", istorijePoseta);
        return map;
    }

    @GetMapping("/delete")
    public String deleteAll(){
        List<Centar> centri = centarRepository.findAll();
        List<User> useri = userRepository.findAll();
        List<IstorijaPoseta> istorijePoseta = istorijaPosetaRepository.findAll();

        istorijePoseta
                .stream()
                .forEach(istorijaPoseta -> {
                    istorijaPosetaRepository.delete(istorijaPoseta);
                });
        useri
                .stream()
                .forEach(user -> userRepository.delete(user));

        centri
                .stream()
                .forEach(centar -> {
                    centarRepository.delete(centar);
                });

        return "Obrisano";
    }
}
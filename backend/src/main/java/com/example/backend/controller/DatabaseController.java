package com.example.backend.controller;


import com.example.backend.models.*;
import com.example.backend.models.enums.Genter;
import com.example.backend.models.enums.Role;
import com.example.backend.models.enums.StatusTermina;
import com.example.backend.repository.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/db")
public class DatabaseController {
    private final CentarRepository centarRepository;
    private final UserRepository userRepository;
    private final TerminRepository terminRepository;
    private  final ZalbaRepository zalbaRepository;

    public DatabaseController(CentarRepository centarRepository,
                              UserRepository userRepository, TerminRepository terminRepository, ZalbaRepository zalbaRepository) {
        this.centarRepository = centarRepository;
        this.userRepository = userRepository;
        this.terminRepository = terminRepository;
        this.zalbaRepository = zalbaRepository;
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


        User user1 = new User();
        user1.setActivation(null);
        user1.setEmail("testDoktor@test.com");
        user1.setAdresa("Mose Pijade 5");
        user1.setRole(Role.ROLE_MEDICINSKO_OSOBLJE);
        user1.setPassword("123");
        user1.setIme("Milica");
        user1.setPhone("06655599955");
        user1.setGrad("Novi Sad");
        user1.setDrzava("Srbija");
        user1.setPol(Genter.FEMALE);
        user1.setPrezime("Jelic");
        user1.setJmbg("1234564321234");

        User user2 = new User();
        user2.setActivation(null);
        user2.setEmail("mladen@test.com");
        user2.setAdresa("Antona Cehova 28");
        user2.setRole(Role.ROLE_KORISNIK);
        user2.setPassword("mladen");
        user2.setIme("Mladen");
        user2.setPhone("062856954");
        user2.setGrad("Vranje");
        user2.setDrzava("Srbija");
        user2.setPol(Genter.MALE);
        user2.setPrezime("Ilic");
        user2.setJmbg("0907994392913");


        User user3 = new User();
        user3.setActivation(null);
        user3.setEmail("testDoktor1@test.com");
        user3.setAdresa("Danice Maksimovic 14");
        user3.setRole(Role.ROLE_MEDICINSKO_OSOBLJE);
        user3.setPassword("123");
        user3.setIme("Ilija");
        user3.setPhone("08899955");
        user3.setGrad("Beograd");
        user3.setDrzava("Srbija");
        user3.setPol(Genter.MALE);
        user3.setPrezime("Simic");
        user3.setJmbg("2234564321234");

        Centar centar = new Centar();
        centar.setAdresa("adresa testa 2");
        centar.setGrad("grad");
        centar.setDrzava("drzava");
        centar.setPhone("1234567890");
        centar.setOcena(0);
        centarRepository.saveAndFlush(centar);


        Centar centar1 = new Centar();
        centar1.setAdresa("Adresa centra 2");
        centar1.setGrad("Novi Sad");
        centar1.setDrzava("Srbija");
        centar1.setPhone("021555666");
        centar1.setOcena(4);
//        centar1.setZaposleni(user1);???
        centarRepository.saveAndFlush(centar1);

        Centar centar2 = new Centar();
        centar2.setAdresa("Nemanjina 2");
        centar2.setGrad("Beograd");
        centar2.setDrzava("Sbija");
        centar2.setPhone("0118889995");
        centar2.setOcena(3);
        centarRepository.saveAndFlush(centar2);


        user.setCentar(centar);///
        userRepository.saveAndFlush(user);

        user1.setCentar(centar1);
        userRepository.saveAndFlush(user1);

//        user2.setCentar(centar1);
        userRepository.saveAndFlush(user2);

        user3.setCentar(centar2);
        userRepository.saveAndFlush(user3);

        Termin termin= new Termin();
        termin.setZakazan(false);
        termin.setStatus(StatusTermina.NOV);
        termin.setCentar(centar2);
        termin.setDatum(Timestamp.valueOf("2023-02-21 11:50:00"));
        terminRepository.save(termin);


        Termin termin1= new Termin();
        termin1.setZakazan(true);
        termin1.setStatus(StatusTermina.OBRAĐEN);
        termin1.setCentar(centar);
        termin1.setDatum(Timestamp.valueOf("2022-09-27 13:00:00"));
        termin1.setPacijent(user);
        terminRepository.save(termin1);

        Termin termin2= new Termin();
        termin2.setZakazan(true);
        termin2.setStatus(StatusTermina.OBRAĐEN);
        termin2.setCentar(centar1);
        termin2.setDatum(Timestamp.valueOf("2023-09-15 08:00:00"));
        termin2.setPacijent(user2);
        terminRepository.save(termin2);


        Termin termin3= new Termin();
        termin3.setZakazan(false);
        termin3.setStatus(StatusTermina.NOV);
        termin3.setCentar(centar1);
        termin3.setDatum(Timestamp.valueOf("2023-02-21 10:20:00"));
        terminRepository.save(termin3);

        Termin termin4= new Termin();
        termin4.setZakazan(false);
        termin4.setStatus(StatusTermina.NOV);
        termin4.setCentar(centar1);
        termin4.setDatum(Timestamp.valueOf("2023-02-15 17:10:00"));
        terminRepository.save(termin4);

        Termin termin5= new Termin();
        termin5.setZakazan(true);
        termin5.setStatus(StatusTermina.OBRAĐEN);
        termin5.setCentar(centar1);
        termin5.setDatum(Timestamp.valueOf("2022-01-18 10:00:00"));
        termin5.setPacijent(user2);
        terminRepository.save(termin5);


        Termin termin6= new Termin();
        termin6.setZakazan(true);
        termin6.setStatus(StatusTermina.OBRAĐEN);
        termin6.setCentar(centar2);
        termin6.setDatum(Timestamp.valueOf("2023-03-18 16:00:00"));
        termin6.setPacijent(user2);
        terminRepository.save(termin6);

        Zalba zalba= new Zalba();
        zalba.setUserEmail(user.getEmail());
        zalba.setTekst("Los odnos prema pacijentima");
        zalbaRepository.save(zalba);

        return "Napunjeno";
    }

    @GetMapping("/get-all-database")
    public Map<String, Object> getAllDatabase(){
        List<Centar> centri = centarRepository.findAll();
        List<User> useri = userRepository.findAll();

        Map<String, Object> map = new HashMap<>();
        map.put("Centri", centri);
        map.put("Useri", useri);
        return map;
    }

    @GetMapping("/delete")
    public String deleteAll(){
        List<Centar> centri = centarRepository.findAll();
        List<User> useri = userRepository.findAll();
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
package com.example.backend.controller;

import com.example.backend.exceptions.NotFoundException;
import com.example.backend.models.Termin;
import com.example.backend.models.User;
import com.example.backend.repository.TerminRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.Impl.TerminServiceImpl;
import com.example.backend.service.UserService;
import com.example.backend.utils.QRGenerator;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController("/termin")
public class TerminController {

  private final UserService userService;
  private final TerminRepository terminRepository;
  private final UserRepository userRepository;
  private final TerminServiceImpl terminService;

  public TerminController(UserService userService,
                          TerminRepository terminRepository,
                          UserRepository userRepository,
                          TerminServiceImpl terminService) {
    this.userService = userService;
    this.terminRepository = terminRepository;
    this.userRepository=userRepository;
    this.terminService = terminService;
  }

  @GetMapping("/qr-codes")
  public List<BufferedImage> getQRCodes(Principal principal) throws Exception {
    List<BufferedImage> qrCodes = new ArrayList<>();
    User user = userService.getActivatedUserFromPrincipal(principal);
    if (user.getRole().equals("ROLE_KORISNIK")) {
      user.getZakazaniTerminiPacijent().forEach(termin -> {
        qrCodes.add(QRGenerator.generateQRCode("http://localhost:8081/termin/" + termin.getId()));
      });
    }
    return qrCodes;
  }

  @GetMapping("/{terminId}")
  public Termin getTerminById(Principal principal, @RequestParam(name = "terminId") Long terminId) throws Exception {
    userService.getActivatedUserFromPrincipal(principal);
    return terminRepository.findById(terminId).orElseThrow(() -> new Exception("Termin ne postoji"));
  }


  @PostMapping("/{terminId}/zakazi")
  public boolean zakaziTermin(Principal principal, @PathVariable(name = "terminId") Long terminId) throws Exception {
    User user = userService.getActivatedUserFromPrincipal(principal);
    return terminService.zakaziTermin(user, terminId);
  }

  @DeleteMapping("/{terminId}/otkazi")
  public boolean otkaziTermin(Principal principal, @PathVariable(name = "terminId") Long terminId) throws Exception {
    User user = userService.getActivatedUserFromPrincipal(principal);
    return terminService.otkaziTermin(user, terminId);
  }

  @GetMapping("/istorija-termina")
  public List<Termin> getIstorijaZaKorisnika(
          Principal principal
  ) throws Exception {
    User user = userService.getActivatedUserFromPrincipal(principal);
    return terminService.istorijaPoseta(user);
  }

}

package com.example.backend.controller;

import com.example.backend.models.Termin;
import com.example.backend.models.User;
import com.example.backend.repository.TerminRepository;
import com.example.backend.service.UserService;
import com.example.backend.utils.QRGenerator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController("/termin")
public class TerminController {

  private final UserService userService;
  private final TerminRepository terminRepository;

  public TerminController(UserService userService, TerminRepository terminRepository) {
    this.userService = userService;
    this.terminRepository = terminRepository;
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
}

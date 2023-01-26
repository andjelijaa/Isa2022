package com.example.backend.controller;

import com.example.backend.models.Termin;
import com.example.backend.models.User;
import com.example.backend.service.TerminService;
import com.example.backend.service.UserService;
import com.example.backend.utils.QRGenerator;
import org.springframework.web.bind.annotation.*;

import java.awt.image.BufferedImage;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/termin")
public class TerminController {

    private final UserService userService;
    private final TerminService terminService;

    public TerminController(UserService userService,
                            TerminService terminService) {
        this.userService = userService;
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
        return terminService.findById(terminId);
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
            Principal principal,
            @RequestParam(name = "datum") boolean datum
    ) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        return terminService.istorijaPoseta(user, datum);
    }

    @GetMapping("/get-zakazane-posete")
    public List<Termin> getZakazani(Principal principal) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        return terminService.getZakazanePosete(user);
    }

}

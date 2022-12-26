package com.example.backend.controller;

import com.example.backend.models.IstorijaPoseta;
import com.example.backend.models.Termin;
import com.example.backend.models.User;
import com.example.backend.models.request.CreateTerminDto;
import com.example.backend.models.request.SortCentarDto;
import com.example.backend.models.request.SortTerminDto;
import com.example.backend.models.request.SortZakazanePoseteDto;
import com.example.backend.models.response.CentarDto;
import com.example.backend.service.Impl.CentarServiceImpl;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController()
@RequestMapping("/centar")
public class CentarController {
    private final UserService userService;
    private final CentarServiceImpl centarServiceImpl;

    public CentarController(UserService userService,
                            CentarServiceImpl centarServiceImpl) {
        this.userService = userService;
        this.centarServiceImpl = centarServiceImpl;
    }

    @GetMapping("/get-all")
    public List<CentarDto> getCentri(@RequestParam(name = "grad") boolean grad,
                                     @RequestParam(name = "ocena") boolean ocena,
                                     @RequestParam(name = "naziv") boolean naziv) {

        return centarServiceImpl.getAllCentri(new SortCentarDto(grad, ocena, naziv));
    }

    @GetMapping("/{centarId}")
    public CentarDto getCentarById(Principal principal,
                                   @RequestParam(name = "centarId") Long centarId) throws Exception {
        userService.getActivatedUserFromPrincipal(principal);

        return centarServiceImpl.getCentarById(centarId);
    }

    @GetMapping("{centarId}/get-all-za-korisnika")
    public List<IstorijaPoseta> getSveZaKorisnika(Principal principal,
                                                  @RequestParam(name = "centarId") Long centarId) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);


        return centarServiceImpl.getIstorijuPosetaZaKorisnikaUCentru(user, centarId);
    }

    @GetMapping("{centarId}/get-zakazane-posete")
    public List<Termin> getZakazanePosete(Principal principal,
                                                  @RequestParam(name = "centarId") Long centarId, @RequestBody SortZakazanePoseteDto sortZakazanePoseteDto) throws Exception {
        userService.getActivatedUserFromPrincipal(principal);
        return centarServiceImpl.getZakazanePosete(centarId, sortZakazanePoseteDto);
    }

    @PostMapping("{centarId}/kreiraj-termin")
    public void kreirajTermin(Principal principal,
                              @RequestParam(name = "centarId") Long centarId,
                              @RequestParam(name = "datum") String datum,
                              @RequestParam(name = "vreme") String vreme) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        if (user.getRole().equals("ADMIN")) {
            centarServiceImpl.kreirajTermin(centarId, datum, vreme);
        } else {
            throw new Exception("Nemate prava da kreirate termin");
        }
    }

    @GetMapping("{centarId}/get-kreirani-termini")
    public List<Termin> getKreiraniTermini(Principal principal,
                                           @RequestParam(name = "centarId") Long centarId,
                                           @RequestBody SortTerminDto sortTerminDto) throws Exception {
        userService.getActivatedUserFromPrincipal(principal);
        return centarServiceImpl.getKreiraniTermini(centarId, sortTerminDto);
    }

    @GetMapping("{centarId}/get-slobodni-termini")
    public List<Termin> getSlobodniTermini(Principal principal,
                                           @RequestParam(name = "centarId") Long centarId) throws Exception {
        userService.getActivatedUserFromPrincipal(principal);
        return centarServiceImpl.getSlobodniTermini(centarId);
    }

    @PostMapping("{centarId}/zakazi-termin")
    public void zakaziTermin(Principal principal,
                             @RequestParam(name = "centarId") Long centarId,
                             @RequestBody CreateTerminDto createTerminDto) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        centarServiceImpl.createTermin(user, centarId, createTerminDto);
    }
}

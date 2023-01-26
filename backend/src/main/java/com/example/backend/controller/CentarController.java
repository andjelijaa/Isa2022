package com.example.backend.controller;

import com.example.backend.models.Centar;
import com.example.backend.models.Termin;
import com.example.backend.models.User;
import com.example.backend.models.request.SortCentarDto;
import com.example.backend.models.request.SortTerminDto;
import com.example.backend.models.request.SortZakazanePoseteDto;
import com.example.backend.models.response.CentarDto;
import com.example.backend.service.CentarService;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController()
@RequestMapping("/centar")
public class CentarController {
    private final UserService userService;
    private final CentarService centarService;

    public CentarController(UserService userService,
                            CentarService centarService) {
        this.userService = userService;
        this.centarService = centarService;
    }

    @GetMapping("/get-all")
    public List<CentarDto> getCentri(@RequestParam(name = "grad") boolean grad,
                                     @RequestParam(name = "ocena") boolean ocena) {

        return centarService.getAllCentri(new SortCentarDto(grad, ocena));
    }

    @GetMapping("/{centarId}")
    public CentarDto getCentarById(Principal principal,
                                   @RequestParam(name = "centarId") Long centarId) throws Exception {
        userService.getActivatedUserFromPrincipal(principal);

        return centarService.getCentarById(centarId);
    }

    @GetMapping("{centarId}/get-zakazane-posete")
    public List<Termin> getZakazanePosete(Principal principal,
                                                  @PathVariable(name = "centarId") Long centarId, @RequestBody SortZakazanePoseteDto sortZakazanePoseteDto) throws Exception {
        userService.getActivatedUserFromPrincipal(principal);
        return centarService.getZakazanePosete(centarId, sortZakazanePoseteDto);
    }

    @PostMapping("{centarId}/kreiraj-termin")
    public void kreirajTermin(Principal principal,
                              @PathVariable(name = "centarId") Long centarId,
                              @RequestParam(name = "datum") String datum,
                              @RequestParam(name = "vreme") String vreme) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        if (user.getRole().equals("ADMIN")) {
            centarService.kreirajTermin(centarId, datum, vreme);
        } else {
            throw new Exception("Nemate prava da kreirate termin");
        }
    }

    @GetMapping("{centarId}/get-slobodni-termini")
    public List<Termin> getSlobodniTermini(Principal principal,
                                           @PathVariable(name = "centarId") Long centarId,
                                           @RequestParam(name = "datum") boolean datum
                                           ) throws Exception {
        userService.getActivatedUserFromPrincipal(principal);
        System.out.println(centarId);
        return centarService.getSlobodniTermini(centarId, datum);
    }

    @GetMapping("/get-centri-za-zalbe")
    public List<Centar> getCentriZaZalbe(Principal principal) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        return centarService.getCentriZaZalbe(user);
    }

    @GetMapping("/get-zaposleni-za-zalbe")
    public List<User> getZaposleniZaZalbe(Principal principal) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        return centarService.getZaposleniZaZalbe(user);
    }


}

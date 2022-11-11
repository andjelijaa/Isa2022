package com.example.backend.controller;

import com.example.backend.models.IstorijaPoseta;
import com.example.backend.models.User;
import com.example.backend.models.ZakazanePosete;
import com.example.backend.models.response.CentarDto;
import com.example.backend.service.Impl.CentarServiceImpl;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController("/centar")
public class CentarController {
    private final UserService userService;
    private final CentarServiceImpl centarServiceImpl;

    public CentarController(UserService userService,
                            CentarServiceImpl centarServiceImpl) {
        this.userService = userService;
        this.centarServiceImpl = centarServiceImpl;
    }

    @GetMapping("/get-all")
    public List<CentarDto> getCentri(Principal principal) {
        return centarServiceImpl.getAllCentri();
    }

    @GetMapping("/{centarId}")
    public CentarDto getCentarById(Principal principal,
                                   @RequestParam(name = "centarId") Long centarId) throws Exception {
        userService.getActivatedUserFromPrincipal(principal);

        return centarServiceImpl.getCentarById(centarId);
    }

    @GetMapping("{centarId}/get-all-za-korisnika/{korisnikId}")
    public List<IstorijaPoseta> getSveZaKorisnika(Principal principal,
                                            @RequestParam(name = "centarId") Long centarId) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);


        return centarServiceImpl.getIstorijuPosetaZaKorisnikaUCentru(user, centarId);
    }

    @GetMapping("{centarId}/get-zakazane-posete")
    public List<ZakazanePosete> getZakazanePosete(Principal principal,
                                                  @RequestParam(name = "centarId") Long centarId) throws Exception {
        userService.getActivatedUserFromPrincipal(principal);
        return centarServiceImpl.getZakazanePosete(centarId);    }
}

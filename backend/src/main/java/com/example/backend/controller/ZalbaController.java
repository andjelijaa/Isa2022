package com.example.backend.controller;

import com.example.backend.exceptions.ForbiddenException;
import com.example.backend.models.User;
import com.example.backend.models.Zalba;
import com.example.backend.repository.ZalbaRepository;
import com.example.backend.service.UserService;
import com.example.backend.service.ZalbaService;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/zalba")
public class ZalbaController {
    private final UserService userService;
    private final ZalbaService zalbaService;

    public ZalbaController(UserService userService, ZalbaService zalbaService) {
        this.userService = userService;
        this.zalbaService = zalbaService;
    }

    @PostMapping("/add")
    public boolean addZalba(Principal principal, @RequestBody Map<String, String> params) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        if(user.getRole().equals("ROLE_KORISNIK")){
            Zalba zalba = new Zalba();
            zalba.setTekst(params.get("text"));
            zalba.setUserEmail(user.getEmail());
            zalbaService.save(zalba);
            return true;
        }
        return true;
    }

    @GetMapping("/get-all")
    public List<Zalba> getAllZalbe(Principal principal) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        if(user.getRole().equals("ROLE_ADMIN")){
            return zalbaService.findAll();
        }
        throw new ForbiddenException();
    }
}
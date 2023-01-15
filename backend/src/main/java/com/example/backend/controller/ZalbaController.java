package com.example.backend.controller;

import com.example.backend.exceptions.ForbiddenException;
import com.example.backend.models.User;
import com.example.backend.models.Zalba;
import com.example.backend.repository.ZalbaRepository;
import com.example.backend.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController("/zalba")
public class ZalbaController {
    private final UserService userService;
    private final ZalbaRepository zalbaRepository;

    public ZalbaController(UserService userService, ZalbaRepository zalbaRepository) {
        this.userService = userService;
        this.zalbaRepository = zalbaRepository;
    }

    @PostMapping("/add")
    public boolean addZalba(Principal principal, @RequestBody Map<String, String> params) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        if(user.getRole().equals("ROLE_KORISNIK")){
            Zalba zalba = new Zalba();
            zalba.setTekst(params.get("text"));
            zalba.setUserEmail(user.getEmail());
            zalbaRepository.save(zalba);
            return true;
        }
        return true;
    }

    @GetMapping("/get-all")
    public List<Zalba> getAllZalbe(Principal principal) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);
        if(user.getRole().equals("ROLE_ADMIN")){
            return zalbaRepository.findAll();
        }
        throw new ForbiddenException();
    }
}
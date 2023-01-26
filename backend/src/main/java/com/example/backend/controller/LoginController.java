package com.example.backend.controller;

import com.example.backend.models.enums.Role;
import com.example.backend.models.User;
import com.example.backend.models.request.RequestUser;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.Impl.EmailServiceImpl;
import com.example.backend.service.UserService;
import com.example.backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST})
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private  UserService userService;

    @Autowired
    private EmailServiceImpl emailServiceImpl;

    public LoginController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        User u = userService.findByEmail(user.getEmail());
        if (u == null) {
            user.setRole(Role.ROLE_KORISNIK);
            String activationCode = emailServiceImpl.generateActivationCode();
            String link = "http://localhost:8081/api/potvrdiEmail/" + activationCode;
            System.out.println("link");
            System.out.println(link);
            user.setActivation(activationCode);
            emailServiceImpl.sendEmail(user.getEmail(), "Aktivacioni link", link);
            userService.save(user);
            return true;
        }
        return false;
    }


    @PostMapping("/login")
    public String login(@RequestBody RequestUser requestUser) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestUser.getEmail(), requestUser.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("invalid username or/and password");
        }
        String jwt = jwtUtils.generateToken(requestUser.getEmail());
        return jwt;
    }

    @GetMapping("/potvrdiEmail/{activationCode}")
    public String potvrdiEmail(@PathVariable String activationCode) {
        User user = userService.findByActivation(activationCode);
        if (user != null) {
            user.setActivation(null);
            userService.save(user);
            return "Uspesno ste aktivirali nalog";
        }
        return "Nalog nije aktiviran";
    }

}




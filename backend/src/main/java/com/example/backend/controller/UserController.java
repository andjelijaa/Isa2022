package com.example.backend.controller;

import com.example.backend.models.User;
import com.example.backend.models.response.UserDto;
import com.example.backend.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get-user")
    public UserDto getUserById(Principal principal) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);

        return new UserDto(user);
    }

    @PutMapping("/update-user")
    public UserDto updateUser(Principal principal, @RequestBody UserDto userDto) throws Exception {
        User user = userService.getActivatedUserFromPrincipal(principal);

        return userService.updateUser(user, userDto);
    }
}

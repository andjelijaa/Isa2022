package com.example.backend.service;

import com.example.backend.models.User;
import com.example.backend.models.response.UserDto;

import java.security.Principal;

public interface UserService{
    User checkActivationCode(String code);
    User findByEmail(String Email);
    void save(User user);
    User getActivatedUserFromPrincipal(Principal principal)throws Exception;

  UserDto updateUser(User user, UserDto userDto);

}
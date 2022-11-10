package com.example.backend.service.Impl;

import com.example.backend.exceptions.UnauthorizedException;
import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import com.example.backend.models.User;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
public class UserServiceImpl implements UserService {


    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User getActivatedUserFromPrincipal(Principal principal)throws Exception{
        if(principal == null){
            return null;
        }
        User u = userRepository.findByEmail(principal.getName());
        if(u != null && u.getActivation() == null){
            return u;

    }else {
        throw new UnauthorizedException();   }
    }

    @Override
    public User checkActivationCode(String code) {
        User user = userRepository.findByActivation(code);
        if(user == null){
            return null;
        }
        return user;
    }

    @Override
    public User findByEmail(String username) {
        return userRepository.findByEmail(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}

package com.example.backend.service.Impl;

import com.example.backend.repository.UserRepository;
import com.example.backend.service.UserService;
import com.example.backend.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {


    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @Override
    public User checkActivationCode(String code) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}

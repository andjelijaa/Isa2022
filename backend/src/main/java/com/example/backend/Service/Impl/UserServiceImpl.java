package com.example.backend.Service.Impl;

import com.example.backend.Service.UserService;
import com.example.backend.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User checkActivationCode(String code) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public void save(User user) {

    }
}

package com.example.backend.service.Impl;

import com.example.backend.models.User;
import com.example.backend.models.Zalba;
import com.example.backend.repository.ZalbaRepository;
import com.example.backend.service.ZalbaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZalbaServiceImpl implements ZalbaService {

    private  final ZalbaRepository zalbaRepository;

    public ZalbaServiceImpl(ZalbaRepository zalbaRepository) {
        this.zalbaRepository = zalbaRepository;
    }

    @Override
    public List<Zalba> findAll(User user) {
        return zalbaRepository.findByUserEmail(user.getEmail());
    }

    @Override
    public void save(Zalba zalba) {
        zalbaRepository.save(zalba);
    }
}

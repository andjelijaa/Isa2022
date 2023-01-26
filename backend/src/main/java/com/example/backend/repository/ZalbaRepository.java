package com.example.backend.repository;


import com.example.backend.models.Zalba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZalbaRepository extends JpaRepository<Zalba, Long> {

    List<Zalba> findByUserEmail(String email);
}
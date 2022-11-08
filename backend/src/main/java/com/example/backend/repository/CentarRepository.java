package com.example.backend.repository;

import com.example.backend.models.Centar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentarRepository extends JpaRepository<Centar, Long> {
}
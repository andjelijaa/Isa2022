package com.example.backend.repository;

import com.example.backend.models.Centar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentarRepository extends JpaRepository<Centar, Long> {
}
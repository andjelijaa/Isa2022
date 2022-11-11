package com.example.backend.repository;

import com.example.backend.models.ZakazanePosete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ZakazanePoseteRepository extends JpaRepository<ZakazanePosete, Long> {
    List<ZakazanePosete> findAllByCentarId(Long centarId);
}
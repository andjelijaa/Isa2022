package com.example.backend.repository;

import com.example.backend.models.IstorijaPoseta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IstorijaPosetaRepository extends JpaRepository<IstorijaPoseta, Long> {

    List<IstorijaPoseta> findAllByDavalacIdAndCentarId(Long id, Long centarId);

}
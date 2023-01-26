package com.example.backend.repository;

import com.example.backend.models.Termin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface TerminRepository extends JpaRepository<Termin, Long> {
    List<Termin> findByPacijentId(Long id);

    Optional<Termin> findByPacijentIdOrderByDatumDesc(Long id); // todo: probati

}
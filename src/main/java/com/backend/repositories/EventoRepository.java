package com.backend.repositories;

import java.util.Optional;

import com.backend.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Long> {

    Optional<Evento> findById(Long id);
    Optional<Evento> findByNombre(String nombre);
}

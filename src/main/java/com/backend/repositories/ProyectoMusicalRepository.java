package com.backend.repositories;

import com.backend.entities.ProyectoMusical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProyectoMusicalRepository extends JpaRepository<ProyectoMusical,Long> {
    Optional<ProyectoMusical> findById(Long id);
    Optional<ProyectoMusical> findByNombre(String nombre);
}
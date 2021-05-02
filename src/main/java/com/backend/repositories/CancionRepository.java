package com.backend.repositories;

import com.backend.entities.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CancionRepository extends JpaRepository<Cancion,Long> {
    Optional<Cancion> findById(Long id);
    Optional<Cancion> findByNombre(String nombre);

}

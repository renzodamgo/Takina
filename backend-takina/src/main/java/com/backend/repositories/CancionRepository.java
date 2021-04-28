package com.backend.repositories;

import com.backend.entities.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CancionRepository extends JpaRepository<Cancion,Long> {

    Optional<Cancion> findById(Long id);

}

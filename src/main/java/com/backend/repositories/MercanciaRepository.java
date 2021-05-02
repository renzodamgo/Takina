package com.backend.repositories;
import com.backend.entities.Mercancia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface MercanciaRepository extends JpaRepository<Mercancia,Long> {
    Optional<Mercancia> findById(Long id);
    Optional<Mercancia> findByNombre(String nombre);
    
}

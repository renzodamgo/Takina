package com.backend.repositories;

import com.backend.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface ArtistaRepository extends JpaRepository<Artista,Long> {

    Optional<Artista> findById(Long Id);
}

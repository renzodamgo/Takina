package com.backend.repositories;

import com.backend.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ArtistaRepository extends JpaRepository<Artista,Long> {
    Optional<Artista> findById(Long id);
    Optional<Artista> findByNombre(String nombre);

    //User Story Buscar Artista por Nombre
    List<Artista> findByNombreContainingIgnoreCase(String nombre);

    //US 004 Buscar artista por genero musical
    List<Artista> findByGeneroMusicalContainingIgnoreCase(String genero_musical);
    //US 005 Buscar artista por departamento origen
    List<Artista> findByDepartamentoOrigenContainingIgnoreCase(String departamento_origen);


}

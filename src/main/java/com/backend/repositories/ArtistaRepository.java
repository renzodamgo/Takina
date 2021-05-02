package com.backend.repositories;

import com.backend.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ArtistaRepository extends JpaRepository<Artista,Long> {
    Optional<Artista> findById(Long id);
    Optional<Artista> findByNombre(String nombre);


    List<Artista> findByNombreContainingIgnoreCase(String nombre);
    List<Artista> findByGeneroMusicalContainingIgnoreCase(String genero_musical);
    List<Artista> findByDepartamentoOrigenContainingIgnoreCase(String departamento_origen);

	@Query("SELECT Art FROM Artista Art")
	List<Artista> findAll();
}

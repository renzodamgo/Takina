package com.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.backend.entities.Cancion;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CancionRepository extends JpaRepository<Cancion,Long> {
	Optional<Cancion> findById(Long id);
	Optional<Cancion> findByNombre(String nombre);

	@Query("SELECT c FROM Cancion c WHERE c.proyecto.id = ?1")
	List<Cancion> findByProyectoId(Long proyectoId);

	List<Cancion> findByNombreContainingIgnoreCase(String nombre);
	List<Cancion> findByGeneroContainingIgnoreCase(String generoMusical);

	void deleteById(Long id);

	@Query("SELECT c FROM Cancion c")
	List<Cancion> findAll();
}

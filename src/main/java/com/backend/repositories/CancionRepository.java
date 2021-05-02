package com.backend.repositories;

import com.backend.entities.Cancion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CancionRepository extends JpaRepository<Cancion,Long> {
    Optional<Cancion> findById(Long id);
    Optional<Cancion> findByNombre(String nombre);

	List<Cancion> findByNombreContainingIgnoreCase(String nombre);

	@Query("SELECT c FROM Cancion c")
	List<Cancion> findAll();
}

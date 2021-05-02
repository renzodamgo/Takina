package com.backend.repositories;

import com.backend.entities.ProyectoMusical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProyectoMusicalRepository extends JpaRepository<ProyectoMusical,Long> {
    Optional<ProyectoMusical> findById(Long id);
    Optional<ProyectoMusical> findByNombre(String nombre);

	List<ProyectoMusical> findByNombreContainingIgnoreCase(String nombre);

	@Query("SELECT Proy FROM ProyectoMusical Proy")
	List<ProyectoMusical> findAll();
}
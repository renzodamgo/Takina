package com.backend.repositories;

import com.backend.entities.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto,Long> {
	Optional<Proyecto> findById(Long id);
	Optional<Proyecto> findByNombre(String nombre);

	List<Proyecto> findByNombreContainingIgnoreCase(String nombre);

	@Query("SELECT pm FROM Proyecto pm")
	List<Proyecto> findAll();
}
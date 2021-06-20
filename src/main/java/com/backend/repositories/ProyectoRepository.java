package com.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.backend.entities.Proyecto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
	Optional<Proyecto> findById(Long id);

	Optional<Proyecto> findByNombre(String nombre);

	void deleteById(Long id);

	List<Proyecto> findByNombreContainingIgnoreCase(String nombre);

	List<Proyecto> findByArtistaId(Long artista_id);

	@Query(nativeQuery = true,
            value = "SELECT * FROM Proyectos ORDER BY fecha DESC LIMIT 10")
	List<Proyecto> findTop10OrderByFecha();

	List<Proyecto> findByGeneroContainingIgnoreCase(String genero);

	@Query("SELECT pm FROM Proyecto pm")
	List<Proyecto> findAll();
}
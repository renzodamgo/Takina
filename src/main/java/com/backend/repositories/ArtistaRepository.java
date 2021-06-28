package com.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.backend.entities.Artista;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<Artista,Long> {
	Optional<Artista> findById(Long id);
	Optional<Artista> findByNombre(String nombre);

	void deleteById(long id);

	List<Artista> findByNombreContainingIgnoreCase(String nombre);
	List<Artista> findByGeneroContainingIgnoreCase(String genero);
	List<Artista> findByDepartamentoContainingIgnoreCase(String departamento);

	@Query("SELECT a FROM Artista a")
	List<Artista> findAll();

	void deleteById(Long artistaId);

}

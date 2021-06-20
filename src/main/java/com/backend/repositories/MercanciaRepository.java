package com.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.backend.entities.Mercancia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MercanciaRepository extends JpaRepository<Mercancia,Long> {
	Optional<Mercancia> findById(Long id);
	Optional<Mercancia> findByNombre(String nombre);
	
	@Query("SELECT m FROM Mercancia m")
	List<Mercancia> findAll();
}

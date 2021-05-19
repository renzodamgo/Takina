package com.backend.repositories;

import com.backend.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface AdministradorRepository extends JpaRepository<Administrador,Long> {
	Optional<Administrador> findById(Long id);

	@Query("SELECT adm FROM Administrador adm")
	List<Administrador> findAll();
}

package com.backend.repositories;

import com.backend.entities.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CreditoRepository extends JpaRepository<Credito,Long> {
    Optional<Credito> findById(Long id);

	@Query("SELECT a FROM Administrador a")
	List<Credito> findAll();
}

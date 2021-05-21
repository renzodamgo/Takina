package com.backend.repositories;

import com.backend.entities.Asistente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente,Long> {
	Optional<Asistente> findById(Long id);

	@Query("SELECT a FROM Asistente a")
	List<Asistente> findAll();
}

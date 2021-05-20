package com.backend.repositories;

import com.backend.entities.Interesado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface InteresadoRepository extends JpaRepository<Interesado,Long> {
	Optional<Interesado> findById(Long id);

	@Query("SELECT i FROM Interesado i")
	List<Interesado> findAll();
}

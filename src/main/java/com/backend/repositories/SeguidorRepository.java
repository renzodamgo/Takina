package com.backend.repositories;

import com.backend.entities.Seguidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface SeguidorRepository extends JpaRepository<Seguidor,Long> {
	Optional<Seguidor> findById(Long id);

	@Query("SELECT s FROM Seguidor s")
	List<Seguidor> findAll();
}

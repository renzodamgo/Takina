package com.backend.repositories;

import com.backend.entities.Invitado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface InvitadoRepository extends JpaRepository<Invitado,Long> {
	Optional<Invitado> findById(Long id);

	@Query("SELECT i FROM Invitado i")
	List<Invitado> findAll();
}

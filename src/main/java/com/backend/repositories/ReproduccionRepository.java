package com.backend.repositories;

import com.backend.entities.Reproduccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.List;

@Repository
public interface ReproduccionRepository extends JpaRepository<Reproduccion,Long> {
	Optional<Reproduccion> findById(Long id);

	@Query("SELECT r FROM Reproduccion r")
	List<Reproduccion> findAll();

	@Query("SELECT r FROM Reproduccion r WHERE r.usuario.id = ?1 and r.cancion.id = ?2")
	Optional<Reproduccion> findByUsuarioIdAndCancionId(Long usuarioId, long cancionId);

	@Query("SELECT r FROM Reproduccion r WHERE r.usuario.id = ?1 ORDER BY r.fecha DESC")
	List<Reproduccion> findByUsuarioIdOrderByFecha(Long usuarioId);
}

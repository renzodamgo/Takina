package com.takina.artist.service.repositories;

import com.takina.artist.service.entities.Seguidor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface SeguidorRepository extends JpaRepository<Seguidor,Long> {
	Optional<Seguidor> findById(Long id);
	void deleteById(Long id);

	@Query("SELECT s FROM Seguidor s")
	List<Seguidor> findAll();

	@Query("SELECT s FROM Seguidor s WHERE s.usuarioId = ?1 and s.artista.id = ?2")
	Optional<Seguidor> findByUsuarioIdAndArtistaId(Long usuarioId, long artistaId);

	@Query(value = "SELECT count(*) FROM seguidores WHERE artistas_id = ?1 AND fecha > ?2",
			nativeQuery = true)
	Integer countByArtistaIdAndGreaterThanFecha(Long artistaId, LocalDateTime fecha);
}

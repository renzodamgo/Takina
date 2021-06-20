package com.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.backend.entities.Asistente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AsistenteRepository extends JpaRepository<Asistente,Long> {
	Optional<Asistente> findById(Long id);

	@Query("SELECT a FROM Asistente a WHERE a.usuario.id = ?1 and a.evento.id = ?2")
	Optional<Asistente> findByUsuarioIdAndEventoId(Long usuarioId, long cancionId);

	@Query("SELECT a FROM Asistente a WHERE a.usuario.id = ?1 ORDER BY a.evento.fecha DESC")
	List<Asistente> findByUsuarioIdOrderByEventoFecha(Long usuarioId);

	@Query("SELECT a FROM Asistente a WHERE a.usuario.id = ?1 ORDER BY a.fecha DESC")
	List<Asistente> findByUsuarioIdOrderByFecha(Long usuarioId);

	@Query("SELECT a FROM Asistente a")
	List<Asistente> findAll();
}

package com.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.backend.entities.Invitado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitadoRepository extends JpaRepository<Invitado,Long> {
	Optional<Invitado> findById(Long id);

	void deleteById(Long id);

	@Query("SELECT inv FROM Invitado inv")
	List<Invitado> findAll();

	@Query("SELECT inv FROM Invitado inv WHERE inv.evento.id = ?1 AND inv.artista.id = ?2")
	Optional<Invitado> findByEventoIdAndArtistaId(Long eventoId, long artistaId);

	@Query("SELECT inv FROM Invitado inv WHERE inv.evento.id = ?1 ORDER BY inv.horaInicio")
	List<Invitado> findByEventoIdOrderByHoraInicio(Long eventoId);

}

package com.takina.artist.service.repositories;

import com.takina.artist.service.entities.Administrador;
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

	@Query("SELECT adm FROM Administrador adm WHERE adm.usuarioId = ?1 AND adm.nivel = ?2")
	List<Administrador> findByUsuarioIdAndNivel(Long id, String nivel);

	@Query("SELECT adm FROM Administrador adm WHERE adm.artista.id = ?1 AND adm.usuarioId = ?2")
	Optional<Administrador> findByArtistaIdAndUsuarioId(Long artistaId, Long usuarioId);

}

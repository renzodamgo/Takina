package com.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.backend.entities.Playlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist,Long> {
	Optional<Playlist> findById(Long id);
	
	@Query("SELECT pl FROM Playlist pl")
	List<Playlist> findAll();

	@Query("SELECT pl FROM Playlist pl WHERE pl.usuario.id = ?1")
	List<Playlist> findByUsuarioId(Long usuarioId);
}

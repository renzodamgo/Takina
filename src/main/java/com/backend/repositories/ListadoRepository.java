package com.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.backend.entities.Listado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ListadoRepository extends JpaRepository<Listado,Long> {
	Optional<Listado> findById(Long id);
	void deleteById(Long id);

	@Query("SELECT lis FROM Listado lis")
	List<Listado> findAll();

	@Query("SELECT lis FROM Listado lis WHERE lis.playlist.id = ?1 AND lis.cancion.id = ?2")
	Optional<Listado> findByPlaylistIdAndCancionId(Long playlistId, Long cancionId);
}

package com.backend.repositories;

import com.backend.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findById(Long id);
    Optional<Usuario> findByNombre(String nombre);

	@Query("SELECT Us FROM Usuario Us")
	List<Usuario> findAll();

	@Query("SELECT Us FROM Usuario Us WHERE us.correo = ?1")
	Usuario findByCorreo(String correo);

	@Query("SELECT Us FROM Usuario Us WHERE us.correo = ?1")
	Usuario findByApodo(String apodo);
}
package com.backend.repositories;

import java.util.List;
import java.util.Optional;

import com.backend.entities.Usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
	Optional<Usuario> findById(Long id);
	Optional<Usuario> findByNombre(String nombre);
	List<Usuario> findByNombreContainingIgnoreCase(String nombre);

	void deleteById(Long id);

	//@Query("SELECT u FROM Usuario u WHERE u.correo = ?1")
	Optional<Usuario> findByCorreo(String correo);

	//@Query("SELECT u FROM Usuario u WHERE u.apodo = ?1")
	Optional<Usuario> findByApodo(String apodo);

	@Query("SELECT u FROM Usuario u WHERE u.apodo = ?1 or u.correo = ?1")
	Optional<Usuario> findByApodoOrCorreo(String login);

	@Query("SELECT u FROM Usuario u")
	List<Usuario> findAll();

}
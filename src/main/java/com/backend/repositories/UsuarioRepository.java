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
	List<Usuario> findByNombreContainingIgnoreCase(String nombre);

	//@Query("SELECT u FROM Usuario u WHERE u.correo = :correo")
	Optional<Usuario> findByCorreo(String correo);

	Optional<Usuario> findByApodoOrCorreo(String login,String login1);
	//@Query("SELECT u FROM Usuario u WHERE u.apodo = ?1")
	Optional<Usuario> findByApodo(String apodo);

	@Query("SELECT u FROM Usuario u")
	List<Usuario> findAll();

}
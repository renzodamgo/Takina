package com.backend.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name="usuarios",
	uniqueConstraints = {
		@UniqueConstraint(name="usuarios_apodo_unique", columnNames="apodo"),
		@UniqueConstraint(name="usuarios_correo_unique", columnNames="correo")
	}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	@Column(
		name="id",
		updatable = false
	)
	private Long id;

	// ---------------------

	@Column(
		name = "apodo",
		nullable = false,
		columnDefinition = "VARCHAR(20)",
		length = 20
	)
	private String apodo;

	// ---------------------

	@Column(
		name = "password",
		nullable = false,
		columnDefinition = "VARCHAR(30)",
		length = 30
	)
	private String password;

	// ----------------------

	@Column(
		name = "nombre",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String nombre;

	// ----------------------

	@Column(
		name = "correo",
		nullable = false,
		columnDefinition = "VARCHAR(30)",
		length = 30
	)
	private String correo;

	// ----------------------

	@Column(
		name="ultimo_ingreso",
		nullable = false,
		columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
	)
	private LocalDateTime ultimoIngreso;

	// ---------------------

	@Column(
		name = "foto_perfil",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String fotoPerfil;

	// ---------------------

	@Column(
		name = "premium",
		nullable = false
	)
	private Boolean premium = false;

	//Relations many to many
	@OneToMany(
		mappedBy = "usuario",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Administrador> administradores = new ArrayList<>();

	@OneToMany(
		mappedBy = "usuario",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Playlist> playlists = new ArrayList<>();

	@OneToMany(
		mappedBy = "usuario",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Reproduccion> reproducciones = new ArrayList<>();

	@OneToMany(
		mappedBy = "usuario",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Seguidor> seguidores = new ArrayList<>();
	
	@OneToMany(
		mappedBy = "usuario",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Asistente> asistentes = new ArrayList<>();
}

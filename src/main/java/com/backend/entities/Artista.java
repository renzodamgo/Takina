package com.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name="artistas",
	uniqueConstraints = {
		@UniqueConstraint(name="artistas_nombre_unique", columnNames = "nombre")
	}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artista {

	// ---------------------

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
		name = "nombre",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String nombre;

	// ---------------------

	@Column(
		name = "biografia",
		nullable = false,
		columnDefinition = "VARCHAR(140)",
		length = 140
	)
	private String biografia;

	// ---------------------
	
	@Column(
		name = "fotoPerfil",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String fotoPerfil;

	// ---------------------

	@Column(
		name = "fotoPortada",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String fotoPortada;

	// --------------------

	@Column(
		name = "departamento",
		nullable = false,
		columnDefinition = "VARCHAR(20)",
		length = 20
	)
	private String departamento;

	 // --------------------

	 @Column(
		name = "genero",
		nullable = false,
		columnDefinition = "VARCHAR(20)",
		length = 20
	)
	private String genero;

	// ---------------------

	@Column(
		name = "seguidoresTotal"
	)
	private Long seguidoresTotal = 0L;
	
	// ---------------------
	
	@Column(
		name = "oyentesTotal"
	)
	private Long oyentesTotal = 0L;

	// --------------------
	// Relaciones con las tablas de base de datos 

	// --------------------
	// Administradores (One Usuario - Many Administrador - One Artista)
	@OneToMany(
		mappedBy = "artista",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Administrador> administradores = new ArrayList<>();

	// --------------------
	// Creditos (One Artista - Many Credito - One Cancion)
	@OneToMany(
		mappedBy = "artista",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Credito> creditos = new ArrayList<>();


	// --------------------
	// (One Artista - Many Mercancia)
	@OneToMany(
		mappedBy = "artista",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Mercancia> mercancias = new ArrayList<>();
	
	// --------------------
	// (One Artista - Many Proyecto Musical)
	@OneToMany(
		mappedBy = "artista",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<ProyectoMusical> proyectos = new ArrayList<>();


	
	/*
	

	//Relations many to many
	@ManyToMany
	@JoinTable(
			name = "seguidores",
			joinColumns = @JoinColumn(name = "artista_id"),
			inverseJoinColumns = @JoinColumn(name = "usuario_id"))
	private List<Usuario> seguidores;

	@ManyToMany
	@JoinTable(
			name = "participantes",
			joinColumns = @JoinColumn(name = "artista_id"),
			inverseJoinColumns = @JoinColumn(name = "evento_id"))
	private List<Evento> participantes;
	*/
}
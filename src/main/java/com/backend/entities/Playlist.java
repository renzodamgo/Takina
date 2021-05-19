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
	name="playlists"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Playlist {
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
		name = "descripcion",
		columnDefinition = "VARCHAR(140)",
		length = 140
	)
	private String descripcion;

	// ---------------------
	@Column(
		name="creacion",
		nullable = false,
		columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
	)
	private LocalDateTime creacion;

	// ---------------------
	@Column(
		name="duracion",
		nullable = false
	)
	private Float duracion = 0F;

	// ---------------------
	@Column(
		name="num_canciones",
		nullable = false
	)
	private Integer numCanciones = 0;

	// ---------------------
	// Relaciones con las tablas de base de datos 
	@ManyToOne
	@JoinColumn(
		name = "usuarios_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "usuarios_playlists_fk"
		)
	)
	private Usuario usuario;

	@OneToMany(
		mappedBy = "playlist",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Listado> listados = new ArrayList<>();
}

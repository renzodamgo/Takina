package com.backend.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name="proyectos"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Proyecto {
	@Id
	@GeneratedValue(
		strategy = GenerationType.IDENTITY
	)
	@Column(
		name="id",
		updatable = false
	)
	private Long id;

	@Column(
		name = "nombre",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String nombre;

	@Column(
		name = "tipo",
		nullable = false,
		columnDefinition = "VARCHAR(20)",
		length = 20
	)
	private String tipo;

	@Column(
		name = "duracion",
		nullable = false
	)
	private float duracion = 0F;

	@Column(
		name="lanzamiento",
		nullable = false,
		columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
	)
	private LocalDateTime lanzamiento;

	@Column(
		name="fecha",
		nullable = false,
		columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
	)
	private LocalDateTime fecha;

	@Column(
		name = "descripcion",
		nullable = false,
		columnDefinition = "VARCHAR(140)",
		length = 140
	)
	private String descripcion;

	@Column(
		name = "foto_portada",
		nullable = false,
		columnDefinition = "VARCHAR(150)",
		length = 150
	)
	private String fotoPortada;

	@Column(
		name = "num_canciones",
		nullable = false
	)
	private Integer numCanciones = 0;

	@Column(
		name = "discografica",
		nullable = false,
		columnDefinition = "VARCHAR(30)",
		length = 30
	)
	private String discografica;

	@Column(
		name = "genero",
		nullable = false,
		columnDefinition = "VARCHAR(20)",
		length = 20
	)
	private String genero;

	@ManyToOne
	@JoinColumn(
		name = "artista_id",
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
				name = "artista_proyectoMusical_fk"
		)
	)
	private Artista artista;

	@OneToMany(
		mappedBy = "proyecto",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Cancion> canciones = new ArrayList<>();	
}
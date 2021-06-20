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
	name="canciones"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancion {
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
		name = "track",
		nullable = false
	)
	private Integer track = 1;

	@Column(
		name = "nombre",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String nombre;

	@Column(
		name = "duracion",
		nullable = false
	)
	private Float duracion;

	@Column(
		name = "audio",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String audio;

	@Column(
		name = "foto_portada",
		nullable = false,
		columnDefinition = "VARCHAR(150)",
		length = 150
	)
	private String FotoPortada;

	@Column(
		name="lanzamiento",
		nullable = false,
		columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
	)
	private LocalDateTime lanzamiento;

	@Column(
		name = "genero",
		nullable = false,
		columnDefinition = "VARCHAR(20)",
		length = 20
	)
	private String genero;
	
	@ManyToOne
	@JoinColumn(
		name = "proyectos_musicales_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "proyectos_musicales_canciones_fk"
		)
	)
	private Proyecto proyecto;

	@OneToMany(
		mappedBy = "cancion",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Credito> creditos = new ArrayList<>();

	@OneToMany(
		mappedBy = "cancion",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Listado> listados = new ArrayList<>();

	@OneToMany(
		mappedBy = "cancion",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Reproduccion> reproducciones = new ArrayList<>();
}
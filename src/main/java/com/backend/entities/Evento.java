package com.backend.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name="eventos",
	uniqueConstraints = {
		@UniqueConstraint(name="eventos_name_unique", columnNames = "nombre")
	}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {
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
		columnDefinition = "VARCHAR(30)",
		length = 30
	)
	private String nombre;

	@Column(
		name = "descripcion",
		nullable = false,
		columnDefinition = "VARCHAR(140)",
		length = 140
	)
	private String descripcion;

	@Column(
		name = "lugar",
		nullable = false,
		columnDefinition = "VARCHAR(30)",
		length = 30
	)
	private String lugar;

	@Column(
		name = "direccion",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String direccion;

	@Column(
		name = "departamento",
		nullable = false,
		columnDefinition = "VARCHAR(20)",
		length = 20
	)
	private String departamento;

	@Column(
			name="fecha",
			nullable = false,
			columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
	)
	private LocalDateTime fecha;

	@Column(
		name = "precio",
		nullable = false
	)
	private Float precio;

	@Column(
		name = "fotoPortada",
		nullable = false,
		columnDefinition = "VARCHAR(150)",
		length = 150
	)
	private String fotoPortada;

	@Column(
		name = "interesados",
		nullable = false
	)
	private Integer interesados = 0;

	@OneToMany(
		mappedBy = "evento",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Invitado> invitados = new ArrayList<>();

	@OneToMany(
		mappedBy = "evento",
		cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
		fetch = FetchType.LAZY
	)
	private List<Asistente> asistentes = new ArrayList<>();
}

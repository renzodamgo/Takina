package com.backend.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name="mercancias"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mercancia {
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
		columnDefinition = "VARCHAR(30)",
		length = 30
	)
	private String nombre;
	
	// ---------------------

	@Column(
		name = "descripcion",
		nullable = false,
		columnDefinition = "VARCHAR(140)",
		length = 140
	)
	private String descripcion;

	// ----------------------

	@Column(
		name = "precio",
		nullable = false
	)
	private Float precio;

	// ----------------------

	@Column(
		name = "foto",
		nullable = false,
		columnDefinition = "VARCHAR(50)",
		length = 50
	)
	private String foto;

	// ----------------------

	// Relaciones con las tablas de base de datos 

	@ManyToOne
	@JoinColumn(
			name = "artista_id",
			nullable = false,
			referencedColumnName = "id",
			foreignKey = @ForeignKey(
					name = "artista_mercancia_fk"
			)
	)
	private Artista artista;
	
}

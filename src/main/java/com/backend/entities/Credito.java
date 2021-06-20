package com.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name="creditos"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credito {
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
		name="descripcion",
		nullable = false,
		columnDefinition = "VARCHAR(30)",
		length = 30
	)
	private String descripcion = "Productor principal";

	@ManyToOne
	@JoinColumn(
		name = "canciones_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "canciones_artistas_fk"
		)
	)
	private Cancion cancion;
	
	@ManyToOne
	@JoinColumn(
		name = "artistas_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "artistas_canciones_fk"
		)
	)
	private Artista artista;
}
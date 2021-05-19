package com.backend.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
		name="administradores"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador {
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

	@Column(
		name = "nivel",
		nullable = false,
		columnDefinition = "VARCHAR(15)",
		length = 15
	)
	private String nivel = "Administrador";


	@Column(
		name="fecha_registro",
		nullable = false,
		columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
	)
	private LocalDateTime fechaRegistro;

	/// Many to One
	@ManyToOne
	@JoinColumn(
		name = "usuarios_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "usuarios_artistas_fk"
		)
	)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(
		name = "artistas_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "artistas_usuarios_fk"
		)
	)
	private Artista artista;
}

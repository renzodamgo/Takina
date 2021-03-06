package com.backend.entities;

import java.time.LocalDateTime;

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
	name="asistentes"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Asistente {
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
		name="fecha",
		nullable = false,
		columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
	)
	private LocalDateTime fecha;

	@ManyToOne
	@JoinColumn(
		name = "usuarios_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "usuarios_eventos_fk"
		)
	)
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(
		name = "eventos_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "eventos_usuarios_fk"
		)
	)
	private Evento evento;
}

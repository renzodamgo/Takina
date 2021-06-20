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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name="seguidores"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seguidor {
	@Id
	@SequenceGenerator(
        name="seguidor_sequence",
        sequenceName = "seguidor_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
    	strategy = GenerationType.SEQUENCE,
    	generator = "seguidor_sequence"
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
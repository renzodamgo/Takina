package com.backend.entities;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
	name="reproducciones"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reproduccion {
	@Id
	@SequenceGenerator(
        name="reproduccion_sequence",
        sequenceName = "reproduccion_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
    	strategy = GenerationType.SEQUENCE,
    	generator = "reproduccion_sequence"
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
			name = "usuarios_canciones_fk"
		)
	)
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(
		name = "canciones_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "canciones_usuarios_fk"
		)
	)
	private Cancion cancion;
}

package com.backend.entities;

//import java.time.LocalDateTime;
import java.time.LocalTime;
import javax.persistence.*;
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
public class Invitado {
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
		name = "hora_inicio",
		nullable = false,
		columnDefinition = "TIME"
	)
	private LocalTime horaInicio;

	@Column(
		name = "hora_fin",
		nullable = false,
		columnDefinition = "TIME"
	)
	private LocalTime horaFin;
	
	@ManyToOne
	@JoinColumn(
		name = "artistas_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "artistas_eventos_fk"
		)
	)
	private Artista artista;

	@ManyToOne
	@JoinColumn(
		name = "eventos_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "eventos_artistas_fk"
		)
	)
	private Evento evento;
}

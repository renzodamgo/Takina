package com.backend.entities;

import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
		name="listados"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Listado {
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
		name="fecha_adicion",
		nullable = false,
		columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
	)
	private LocalDateTime fechaAdicion;

	/// Many to One
	@ManyToOne
	@JoinColumn(
		name = "playlist_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "playlist_cancion_fk"
		)
	)
	private Playlist playlist;
	
	@ManyToOne
	@JoinColumn(
		name = "cancion_id",
		updatable = false,
		nullable = false,
		referencedColumnName = "id",
		foreignKey = @ForeignKey(
			name = "cancion_playlist_fk"
		)
	)
	private Cancion cancion;
}

package com.backend.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="canciones"
        // uniqueConstraints = {
        //         @UniqueConstraint(name="cancion_name_unique",
        //                 columnNames = "name")
        // }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancion {

    @Id
    @SequenceGenerator(
            name="cancion_sequence",
            sequenceName = "cancion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cancion_sequence"
    )

    // ---------------------

    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    // ---------------------

    @Column(
        name = "nombre",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String nombre;

    // ----------------------

	@Column(
        name = "duracion",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private float duracion;
	// EJM: 3.4 => 3 minutos 24 segundos
	// int(duracion) minutos - int(duracion%1 * 60) segundos

    // ---------------------

    @Column(
        name = "audio",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String audio;

    // ---------------------

    @Column(
        name = "imagen",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String imagen;

    // ----------------------

    @Column(
            name="fechaLanzamiento",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime fechaLanzamiento;

    // ---------------------

	@Column(
        name = "genero_musical",
        nullable = false,
		columnDefinition = "TEXT"
    )
    private String genero_musical;
	
	// ---------------------

    // Relaciones con las tablas de base de datos 

    @ManyToOne
    @JoinColumn(
            name = "proyectoMusical_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "proyectoMusical_cancion_fk"
            )
    )
    private ProyectoMusical proyecto;

    //Relations many to many
    @ManyToMany(mappedBy = "listaCancionArtista")
    List<Artista> listaArtistaCanciones;

    @ManyToMany(mappedBy = "listaCancionReproducciones")
    List<ListaReproduccion> listaReproduccionCanciones;
}

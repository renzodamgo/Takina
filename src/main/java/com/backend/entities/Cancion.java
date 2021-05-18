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
        //uniqueConstraints = {
        //        @UniqueConstraint(name="canciones_nombre_unique",columnNames = "nombre")
        //}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cancion {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
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
        columnDefinition = "VARCHAR(50)",
		length = 50
    )
    private String nombre;

    // ----------------------
	
	@Column(
        name = "duracion",
        nullable = false
    )
    private float duracion;
	// EJM: 3.4 => 3 minutos 24 segundos
	// int(duracion) minutos - int(duracion%1 * 60) segundos

    // ---------------------

    @Column(
        name = "audio",
        nullable = false,
        columnDefinition = "VARCHAR(50)",
		length = 50
    )
    private String audio;

    // ---------------------

    @Column(
        name = "foto_portada",
        nullable = false,
        columnDefinition = "VARCHAR(50)",
		length = 50
    )
    private String FotoPortada;

    // ----------------------

    @Column(
        name="lanzamiento",
        nullable = false,
		columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime lanzamiento;

    // ---------------------

	@Column(
        name = "genero",
        nullable = false,
		columnDefinition = "VARCHAR(20)",
		length = 20
    )
    private String genero;
	
	// --------------------
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

	/*
    //Relations many to many
    @ManyToMany(mappedBy = "colaboradores")
    List<Artista> colaboradores;

    @ManyToMany(mappedBy = "playlistCanciones")
    List<ListaReproduccion> playlistCanciones;
	*/
}

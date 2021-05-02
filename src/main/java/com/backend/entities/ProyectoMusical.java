package com.backend.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="proyectosMusicales"
        // uniqueConstraints = {
        //         @UniqueConstraint(name="proyectoMusical_name_unique",
        //                 columnNames = "name")
        // }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyectoMusical {

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
        columnDefinition = "TEXT"
    )
    private String nombre;

    // ----------------------

    @Column(
        name = "tipoProyecto",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String tipoProyecto;

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
    	name="lanzamiento",
        nullable = false
    )
    private LocalDateTime lanzamiento;

    // ----------------------
    
    @Column(
        name = "descripcion",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String descripcion;

    // ----------------------

    @Column(
        name = "rutaImagen",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String rutaImagen;

    // ----------------------

    @Column(
        name = "canciones",
        nullable = false
    )
    private Integer canciones;

    // ----------------------

    @Column(
        name = "discografica",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String discografica;

	// ----------------------

    @Column(
        name = "generoMusical",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String generoMusical;

    // ----------------------

    // Relaciones con las tablas de base de datos 

    @ManyToOne
    @JoinColumn(
            name = "artista_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "artista_proyectoMusical_fk"
            )
    )
    private Artista artista;

    // ---------------------

    @OneToMany(
        mappedBy = "proyecto",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY
    )
    private List<Cancion> cancion = new ArrayList<>();
    
}

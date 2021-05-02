package com.backend.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="mercancia"
        // uniqueConstraints = {
        //         @UniqueConstraint(name="mercancia_name_unique",
        //                 columnNames = "name")
        // }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mercancia {

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
	
	// ---------------------

    @Column(
        name = "descripcion",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String descripcion;

    // ----------------------

    @Column(
        name = "precio",
        nullable = false
    )
    private Float precio;

    // ----------------------

    @Column(
        name = "foto",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String foto;

    // ----------------------

    // Relaciones con las tablas de base de datos 

    @ManyToOne
    @JoinColumn(
            name = "artista_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "artista_mercancia_fk"
            )
    )
    private Artista artista;
    
}

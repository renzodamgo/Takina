package com.backend.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="canciones",
        uniqueConstraints = {
                @UniqueConstraint(name="cancione_name_unique",
                        columnNames = "name")
        }
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

    // TODO: DURACION DE LA CANCION (NOSE COMO PONERLO)

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
            name="lanzamiento",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime lanzamiento;

    // ---------------------

    // TODO: faltan las relaciones correspondientes 
    
}

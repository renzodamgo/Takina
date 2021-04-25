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
    @SequenceGenerator(
            name="mercancia_sequence",
            sequenceName = "mercancia_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mercancia_sequence"
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

    // TODO: relacion con artista de uno (artista) a muchos (mercancia)
    
}

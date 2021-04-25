package com.backend.entities;
import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="eventos"
        // uniqueConstraints = {
        //         @UniqueConstraint(name="eventos_name_unique",
        //                 columnNames = "name")
        // }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Eventos {

    @Id
    @SequenceGenerator(
            name="eventos_sequence",
            sequenceName = "eventos_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "eventos_sequence"
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
        name = "lugar",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String lugar;

    // ----------------------

    @Column(
            name="fecha",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime fecha;

    // ----------------------
    
    @Column(
        name = "precio",
        nullable = false
    )
    private Float precio;

    // ----------------------

    @Column(
        name = "portada",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String portada;

    // ----------------------

    @Column(
        name = "interesados",
        nullable = false
    )
    private Integer interesados;

    // ----------------------

    // TODO: muchos a muchos con artista
}

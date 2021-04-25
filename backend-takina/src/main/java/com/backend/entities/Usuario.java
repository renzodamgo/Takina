package com.backend.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="usuarios"
        // uniqueConstraints = {
        //         @UniqueConstraint(name="usuarios_name_unique",
        //                 columnNames = "name")
        // }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
    @Id
    @SequenceGenerator(
            name="usuario_sequence",
            sequenceName = "usuario_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "usuario_sequence"
    )

    // ---------------------

    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    // ---------------------

    @Column(
        name = "apodo",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String apodo;

    // ---------------------

    @Column(
        name = "contraseña",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private Long contraseña;

    // ----------------------

    @Column(
        name = "nombre",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String nombre;

    // ----------------------

    @Column(
        name = "correo",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String correo;

    // ----------------------

    @Column(
            name="ultimoIngreso",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime ultimoIngreso;

    // ---------------------

    @Column(
        name = "fotoPerfil",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String fotoPerfil;

    // ---------------------

    @Column(
        name = "premium",
        nullable = false
    )
    private boolean premium;

    // ---------------------

    // TODO: falta relacion con artista (muchos a muchos)

}

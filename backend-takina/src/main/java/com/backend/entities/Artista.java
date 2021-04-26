package com.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="artistas"
        // uniqueConstraints = {
        //         @UniqueConstraint(name="artistas_name_unique",
        //                 columnNames = "name")
        // }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artista {

    @Id
    @SequenceGenerator(
            name="artista_sequence",
            sequenceName = "artista_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "artista_sequence"
    )

    // ---------------------

    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    // ---------------------

    @Column(
        name = "biografia",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String biografia;

    // ---------------------

    @Column(
        name = "foto",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String foto;

    // ---------------------

    @Column(
        name = "seguidores",
        nullable = false
    )
    private Long seguidores;

    // ---------------------

    @Column(
        name = "oyentesMes",
        nullable = false
    )
    private Long oyentesMes;

    // --------------------

    // Relaciones con las tablas de base de datos 

    @OneToMany(
        mappedBy = "artista",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY
    )
    private List<Mercancia> listas = new ArrayList<>();

    // --------------------
    
    @OneToMany(
        mappedBy = "artista",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY
    )
    private List<ProyectoMusical> proyecto = new ArrayList<>();
    
}
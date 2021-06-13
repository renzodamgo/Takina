package com.takina.artist.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name="artistas",
        uniqueConstraints = {
                @UniqueConstraint(name="artistas_nombre_unique", columnNames = "nombre")
        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Artista {

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

    // ---------------------

    @Column(
            name = "nombre",
            nullable = false,
            columnDefinition = "VARCHAR(50)",
            length = 50
    )
    private String nombre;

    // ---------------------

    @Column(
            name = "biografia",
            nullable = false,
            columnDefinition = "VARCHAR(140)",
            length = 140
    )
    private String biografia;

    // ---------------------

    @Column(
            name = "foto_perfil",
            nullable = false,
            columnDefinition = "VARCHAR(50)",
            length = 50
    )
    private String fotoPerfil;

    // ---------------------

    @Column(
            name = "fotoPortada",
            nullable = false,
            columnDefinition = "VARCHAR(50)",
            length = 50
    )
    private String fotoPortada;

    // --------------------

    @Column(
            name = "departamento",
            nullable = false,
            columnDefinition = "VARCHAR(20)",
            length = 20
    )
    private String departamento;

    // --------------------

    @Column(
            name = "genero",
            nullable = false,
            columnDefinition = "VARCHAR(20)",
            length = 20
    )
    private String genero;

    // ---------------------

    @Column(
            name = "total_seguidores"
    )
    private Long totalSeguidores = 0L;

    // ---------------------

    @Column(
            name = "reproducciones_total"
    )
    private Long totalReproducciones = 0L;

    // --------------------
    // Relaciones con las tablas de base de datos

    // --------------------
    // Administradores (One Usuario - Many Administrador - One Artista)
    @OneToMany(
            mappedBy = "artista",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Administrador> administradores = new ArrayList<>();

    @OneToMany(
            mappedBy = "artista",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            fetch = FetchType.LAZY
    )
    private List<Seguidor> seguidores = new ArrayList<>();


}
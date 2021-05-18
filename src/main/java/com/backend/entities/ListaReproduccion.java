package com.backend.entities;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="listas_reproduccion"
        //uniqueConstraints = {
        //        @UniqueConstraint(name="listas_reproduccion_name_unique", columnNames = "name")
        //}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaReproduccion {
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

    // ---------------------

    @Column(
        name = "descripcion",
        nullable = false,
        columnDefinition = "VARCHAR(140)",
        length = 140
    )
    private String descripcion;

    // ---------------------

    @Column(
            name="creacion",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime creacion;

    // ---------------------

    // TODO: FALTA PONER LA DURACION DE LA CANCION
    @Column(
        name="duracion",
        nullable = false,
        columnDefinition = "FLOAT TO TIME"
    )
    private Float duracion;

    // ---------------------

    @Column(
            name="numeroCanciones",
            nullable = false
    )
    private Integer numeroCanciones = 0;

    // ---------------------

    // Relaciones con las tablas de base de datos 
    @ManyToOne
    @JoinColumn(
            name = "usuario_id",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "usuario_listaReproduccion_fk"
            )
    )
    private Usuario usuario;

    /* 
    @ManyToMany
    @JoinTable(
            name = "playlist_canciones",
            joinColumns = @JoinColumn(name = "lista_reproduccion_id"),
            inverseJoinColumns = @JoinColumn(name = "cancion_id"))
    private List<Cancion> playlistCanciones;
    */



}

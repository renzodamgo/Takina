package com.backend.entities;
import java.time.LocalDateTime;
import java.util.List;

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
public class Evento {

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
        name = "lugar",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String lugar;

	// ----------------------

    @Column(
        name = "departamento",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String departamento;

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
        name = "fotoPortada",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String fotoPortada;

    // ----------------------

    @Column(
        name = "interesados",
        nullable = false
    )
    private Integer interesados = 0;

    // Relations many to many
    @ManyToMany(mappedBy = "listaEventoArtistas")
    List<Artista> listaArtistasEventos;

}

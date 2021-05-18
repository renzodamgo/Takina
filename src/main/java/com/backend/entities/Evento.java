package com.backend.entities;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="eventos",
        uniqueConstraints = {
                @UniqueConstraint(name="eventos_name_unique", columnNames = "nombre")
        }
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
        columnDefinition = "VARCHAR(30)",
        length = 30
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

    // ----------------------
	// venue = lugar de eventos
    @Column(
        name = "lugar",
        nullable = false,
        columnDefinition = "VARCHAR(30)",
        length = 30
    )
    private String lugar;

	// ----------------------

	@Column(
        name = "direccion",
        nullable = false,
        columnDefinition = "VARCHAR(50)",
        length = 50
    )
    private String direccion;

	// ----------------------

    @Column(
        name = "departamento",
        nullable = false,
        columnDefinition = "VARCHAR(20)",
        length = 20
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
        columnDefinition = "VARCHAR(50)",
        length = 50
    )
    private String fotoPortada;

    // ----------------------

    @Column(
        name = "interesados",
        nullable = false
    )
    private Integer interesados = 0;

    // Relations many to many
	/*
    @ManyToMany(mappedBy = "participantes")
    private List<Artista> participantes;
	*/
}

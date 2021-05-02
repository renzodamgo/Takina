package com.backend.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="usuarios"
        //uniqueConstraints = {
        //        @UniqueConstraint(name="usuario_apodo_unique",columnNames="apodo"),
		//		@UniqueConstraint(name="usuario_correo_unique",columnNames="correo")
        //}
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    
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
        name = "apodo",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String apodo;

    // ---------------------

    @Column(
        name = "password",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String password;

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
        name = "premium"
    )
    private boolean premium = false;

    // ---------------------

    @OneToMany(
        mappedBy = "usuario",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY
    )
    private List<ListaReproduccion> listas = new ArrayList<>();

    //Relations many to many
    @ManyToMany(mappedBy = "listaAdministradoresArtista")
    private List<Artista> listaArtistaUsuarios;

}

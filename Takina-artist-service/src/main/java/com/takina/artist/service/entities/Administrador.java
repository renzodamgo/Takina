package com.takina.artist.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name="administradores"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador {
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

    @Column(
            name = "nivel",
            nullable = false,
            columnDefinition = "VARCHAR(15)",
            length = 15
    )
    private String nivel = "Administrador";


    @Column(
            name="fecha_registro",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime fechaRegistro;

    private Long usuarioId;

    @ManyToOne
    @JoinColumn(
            name = "artistas_id",
            updatable = false,
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "artistas_usuarios_fk"
            )
    )
    private Artista artista;
}

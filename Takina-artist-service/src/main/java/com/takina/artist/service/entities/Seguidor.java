package com.takina.artist.service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name="seguidores"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seguidor {
    @Id
    @SequenceGenerator(
            name="seguidor_sequence",
            sequenceName = "seguidor_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "seguidor_sequence"
    )
    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    @Column(
            name="fecha",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime fecha;

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

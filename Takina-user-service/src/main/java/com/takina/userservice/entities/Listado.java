package com.takina.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(
        name="listados"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Listado {
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
            name="fecha_adicion",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime fechaAdicion;

    /// Many to One
    @ManyToOne
    @JoinColumn(
            name = "playlist_id",
            updatable = false,
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "playlist_cancion_fk"
            )
    )
    private Playlist playlist;

    @Column(
            name="cancion_id"
    )
    private Long cancionId;
}
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
        name="listaRepreduccion"
        // uniqueConstraints = {
        //         @UniqueConstraint(name="listaRepreduccion_name_unique",
        //                 columnNames = "name")
        // }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaReproduccion {
    
    @Id
    @SequenceGenerator(
            name="listaRepreduccion_sequence",
            sequenceName = "listaRepreduccion_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "listaRepreduccion_sequence"
    )

    // ---------------------

    @Column(
            name="id",
            updatable = false
    )
    private Long id;

    // ---------------------

    @Column(
            name="creacion",
            nullable = false,
            columnDefinition = "TIMESTAMP WITHOUT TIME ZONE"
    )
    private LocalDateTime creacion;

    // ---------------------

    // TODOO: FALTA PONER LA DURACION DE LA CANCION

    // ---------------------

    @Column(
            name="numeroCanciones",
            nullable = false
    )
    private Integer numeroCanciones;

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

    // -----------------------

//    @OneToMany(
//            mappedBy = "listaReproduccion",
//            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
//            fetch = FetchType.LAZY
//    )
//    private List<ListaReproduccion> listaReproducciones = new ArrayList<>();


}

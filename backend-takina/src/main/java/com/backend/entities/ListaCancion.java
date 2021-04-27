package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Lista_Cancion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaCancion {
    @EmbeddedId
    private ListaCancionPK id;

//    @ManyToOne
//    @JoinColumn(name = "Lista_Reproduccion_c_playlist", insertable = false, updatable = false)
//    private ListaReproduccion listaReproduccion;
//
//    @ManyToOne
//    @JoinColumn(name = "Cancion_c_cancion", insertable = false, updatable = false)
//    private Cancion cancion;
}

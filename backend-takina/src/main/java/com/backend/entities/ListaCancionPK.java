package com.backend.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ListaCancionPK implements Serializable {
    @Column(name = "Lista_Reproduccion_c_playlist")
    private Long ListaReproduccionCPlaylist;
    @Column(name = "Cancion_c_cancion")
    private Long CancionCCancion;
}

package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ListaCancionArtistaPK implements Serializable {
    @Column(name = "Cancion_C_cancion")
    private Long cCancion;

    @Column(name = "Artista_C_artista")
    private Long cArtista;
}

package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Lista_Cancion_Artista")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaCancionArtista {
    @EmbeddedId
    private ListaCancionArtistaPK id;

    @ManyToOne
    @JoinColumn(name = "Artista_c_artista", insertable = false, updatable = false)
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "Cancion_C_cancion", insertable = false, updatable = false)
    private Cancion cancion;
}

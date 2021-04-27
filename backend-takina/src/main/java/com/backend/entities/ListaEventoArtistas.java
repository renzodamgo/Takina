package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Lista_Evento_Artistas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaEventoArtistas {
    @EmbeddedId
    private ListaEventoArtistasPK id;

    @ManyToOne
    @JoinColumn(name = "Artista_C_artista", insertable = false, updatable = false)
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "Evento_C_evento", insertable = false, updatable = false)
    private Eventos eventos;
}

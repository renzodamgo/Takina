package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class ListaEventoArtistasPK implements Serializable {
    @Column(name = "Artista_C_artista")
    private Long cArtista;

    @Column(name = "Evento_C_evento")
    private Long cEvento;
}

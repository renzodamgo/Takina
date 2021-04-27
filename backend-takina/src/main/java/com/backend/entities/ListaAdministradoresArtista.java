package com.backend.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Lista_Administradores_Artista")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListaAdministradoresArtista {
    @EmbeddedId
    private ListaAdministradoresArtistaPK id;

    @ManyToOne
    @JoinColumn(name = "Artista_c_artista", insertable = false, updatable = false)
    private Artista artista;

    @ManyToOne
    @JoinColumn(name = "Usuario_c_usuario", insertable = false, updatable = false)
    private Usuario usuario;

}

package com.backend.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ListaAdministradoresArtistaPK implements Serializable {
    @Column(name = "Artista_c_artista")
    private Long ArtistaCArtista;
    @Column(name = "Usuario_c_usuario")
    private Long UsuarioCUsuario;
}

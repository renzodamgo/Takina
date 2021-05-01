package com.backend.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name="artistas"
        // uniqueConstraints = {
        //         @UniqueConstraint(name="artistas_name_unique",
        //                 columnNames = "name")
        // }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
//TODO Que el Artista y el ArtistaDTO Tengan los Mismos nombres de las variables
public class Artista {

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

    // ---------------------
	
	@Column(
        name = "nombre",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String nombre;

    // ---------------------

    @Column(
        name = "biografia",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String biografia;

    // ---------------------
	
    @Column(
        name = "foto_perfil",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String foto_perfil;

	// ---------------------

    @Column(
        name = "foto_portada",
        nullable = false,
        columnDefinition = "TEXT"
    )
    private String foto_portada;

    // --------------------

	@Column(
        name = "departamento_origen",
        nullable = false,
		columnDefinition = "TEXT"
    )
    private String departamento_origen;

	 // --------------------

	 @Column(
        name = "genero_musical",
        nullable = false,
		columnDefinition = "TEXT"
    )
    private String genero_musical;

	// ---------------------

	@Column(
		name = "seguidores"
	)
	private Long seguidores = 0L;
	
	// ---------------------
	
	@Column(
		name = "oyentes_total"
	)
	private Long oyentes_total = 0L;

    // --------------------

    // Relaciones con las tablas de base de datos 

    @OneToMany(
        mappedBy = "artista",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY
    )
    private List<Mercancia> mercancias = new ArrayList<>();

    // --------------------
    
    @OneToMany(
        mappedBy = "artista",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        fetch = FetchType.LAZY
    )
    private List<ProyectoMusical> proyectos = new ArrayList<>();

    //Relations many to many
    @ManyToMany
    @JoinTable(
            name = "lista_cancion_artista",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "cancion_id"))
    List<Cancion> listaCancionArtista;

    @ManyToMany
    @JoinTable(
            name = "lista_administradores_artista",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    List<Usuario> listaAdministradoresArtista;

    @ManyToMany
    @JoinTable(
            name = "lista_evento_artistas",
            joinColumns = @JoinColumn(name = "artista_id"),
            inverseJoinColumns = @JoinColumn(name = "evento_id"))
    List<Eventos> listaEventosArtistas;
}
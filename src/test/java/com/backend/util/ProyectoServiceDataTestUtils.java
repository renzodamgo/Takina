package com.backend.util;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.backend.dtos.creates.CreateProyectoDto;
import com.backend.entities.Artista;
import com.backend.entities.Proyecto;
import com.backend.entities.Usuario;

public class ProyectoServiceDataTestUtils {
	public static Usuario getMockUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		usuario.setApodo("renxzen");
		usuario.setCorreo("renxzen@gmail.com");
		usuario.setPassword("LoHagoPorTi");
		usuario.setNombre("Renzo Mondragón");
		usuario.setFotoPerfil("Oni/gaa.png");
		usuario.setPremium(false);
		usuario.setUltimoIngreso(LocalDateTime.now());
		usuario.setPlaylists(new ArrayList<>());
		return usuario;
	}
	
	public static Artista getMockArtista() {
		Artista artista = new Artista();
		artista.setId(1L);
		artista.setNombre("Keiko Fujimori");
		artista.setFotoPerfil("600millones.png");
		artista.setFotoPortada("Odebrecht.jpg");
		artista.setBiografia("Una peruana con sueños de dictadura.");
		artista.setTotalSeguidores(0L);
		artista.setTotalReproducciones(0L);
		artista.setDepartamento("Lima");
		artista.setGenero("Pop Rock");
		artista.setAdministradores(new ArrayList<>());
		artista.setProyectos(new ArrayList<>());
		artista.setSeguidores(new ArrayList<>());
		return artista;
	}

	public static Proyecto getMockProyecto() {
		Proyecto proyecto = new Proyecto();
		proyecto.setId(1L);
		proyecto.setNombre("Dictadura Romantica");
		proyecto.setTipo("LP");
		proyecto.setDuracion(0F);
		proyecto.setDescripcion("Khe mas nos queda.");
		proyecto.setNumCanciones(0);
		proyecto.setLanzamiento(LocalDateTime.parse("2019-12-24T10:00:16"));
		proyecto.setFecha(LocalDateTime.now());
		proyecto.setDiscografica("Fuerza Popular Records");
		proyecto.setFotoPortada("kyara_edicion.jpg");
		proyecto.setGenero("Korean Pop");
		//proyecto.setArtista();
		proyecto.setCanciones(new ArrayList<>());
		return proyecto;
	}

	public static CreateProyectoDto getMockCreateProyectoDto() {
		CreateProyectoDto createProyectoDto = new CreateProyectoDto();
		createProyectoDto.setNombre("Dictadura Romantica");
		createProyectoDto.setTipo("LP");
		createProyectoDto.setDuracion(0F);
		createProyectoDto.setDescripcion("Khe mas nos queda.");
		createProyectoDto.setLanzamiento(LocalDateTime.parse("2019-12-24T10:00:16"));
		createProyectoDto.setDiscografica("Fuerza Popular Records");
		createProyectoDto.setFotoPortada("kyara_edicion.jpg");
		createProyectoDto.setGenero("Korean Pop");
		//createProyectoDto.setArtistaId(1L);
		return createProyectoDto;
	}
}

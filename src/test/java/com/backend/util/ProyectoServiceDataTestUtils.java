package com.backend.util;

import com.backend.dtos.creates.*;
import com.backend.entities.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

	public static CreateProyectoDto getMockCreateProyectoDto() {
		CreateProyectoDto createProyectoDto = new CreateProyectoDto();
		createProyectoDto.setNombre("Dictadura Romantica");
		createProyectoDto.setTipo("LP");
		createProyectoDto.setDuracion(0F);
		createProyectoDto.setDescripcion("Khe mas nos queda que escuchar este album en ves de escuchar el otro.");
		createProyectoDto.setLanzamiento(LocalDateTime.parse("2019-12-24T10:00:16"));
		createProyectoDto.setDiscografica("Fuerza Popular Records");
		createProyectoDto.setFotoPortada("kyara_edicion.jpg");
		createProyectoDto.setGenero("Korean Pop");
		createProyectoDto.setArtistaId(1L);
		return createProyectoDto;
	}

	public static CreateCancionDto getMockCreateCancionDto() {
		CreateCancionDto createCancionDto = new CreateCancionDto();
		createCancionDto.setNombre("600 millones");
		createCancionDto.setDuracion(2.2F);
		createCancionDto.setAudio("huelga.mp3");
		createCancionDto.setProyectoId(1L);
		return createCancionDto;
	}

	public static CreateCancionProyectoDto getMockCreateCancionProyectoDto() {
		CreateCancionProyectoDto createCancionProyectoDto = new CreateCancionProyectoDto();
		createCancionProyectoDto.setNombre("Vladimiro es mi padrino");
		createCancionProyectoDto.setDuracion(4.07F);
		createCancionProyectoDto.setAudio("huelga.mp3");
		createCancionProyectoDto.setDescripcion("Una cancion romantica para el padrino que me pago los estudios");
		createCancionProyectoDto.setLanzamiento(LocalDateTime.parse("2021-01-26T11:00:00"));
		createCancionProyectoDto.setDiscografica("Fuerza Popular Records");
		createCancionProyectoDto.setFotoPortada("really/accesoTotal.jpg");
		createCancionProyectoDto.setArtistaId(1L);
		return createCancionProyectoDto;
	}
}

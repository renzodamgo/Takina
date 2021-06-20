package com.backend.util;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.backend.dtos.creates.CreateCancionDto;
import com.backend.dtos.creates.CreateCancionProyectoDto;
import com.backend.entities.Cancion;
import com.backend.entities.Proyecto;

public class CancionServiceDataTestUtils {
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

	public static Cancion getMockCancion() {
		Cancion cancion = new Cancion();
		//cancion.setId();
		cancion.setNombre("600 millones");
		cancion.setAudio("huelga.mp3");
		cancion.setFotoPortada("kyara_edicion.jpg");
		cancion.setLanzamiento(LocalDateTime.parse("2019-12-24T10:00:16"));
		cancion.setGenero("Korean Pop");
		cancion.setDuracion(1.2F);
		//cancion.setProyectoId();
		cancion.setCreditos(new ArrayList<>());
		return cancion;
	}

	public static CreateCancionDto getMockCreateCancionDto() {
		CreateCancionDto createCancionDto = new CreateCancionDto();
		createCancionDto.setNombre("600 millones");
		createCancionDto.setDuracion(2.2F);
		createCancionDto.setAudio("huelga.mp3");
		//createCancionDto.setProyectoId(1L);
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
		//createCancionProyectoDto.setArtistaId(1L);
		return createCancionProyectoDto;
	}
}

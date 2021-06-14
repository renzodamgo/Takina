package com.backend.util;

import com.backend.dtos.*;
import com.backend.dtos.creates.*;


public class ArtistaServiceDataTestUtils {

	public static CreateArtistaDto getMockCreateArtista() {
		CreateArtistaDto createArtista = new CreateArtistaDto();
		createArtista.setNombre("Pedro Castillo");
		createArtista.setBiografia("Un cajamarquino con sueños de comunismo.");
		createArtista.setFotoPerfil("cerron.png");
		createArtista.setFotoPortada("cubaLibre.jpg");
		createArtista.setDepartamento("Cajamarca");
		createArtista.setGenero("Floklore");
		createArtista.setUsuarioId(1L);
		return createArtista;
	}


}

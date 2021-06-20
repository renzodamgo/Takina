package com.backend.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;

import java.util.Optional;

import com.backend.dtos.creates.CreateCancionDto;
import com.backend.dtos.creates.CreateCancionProyectoDto;
import com.backend.entities.Cancion;
import com.backend.entities.Proyecto;
import com.backend.exceptions.ArtistaNotFoundException;
import com.backend.exceptions.CancionNotFoundException;
import com.backend.exceptions.IncorrectCancionException;
import com.backend.exceptions.ProyectoNotFoundException;
import com.backend.exceptions.UsuarioNotFoundException;
import com.backend.repositories.ArtistaRepository;
import com.backend.repositories.CancionRepository;
import com.backend.repositories.CreditoRepository;
import com.backend.repositories.ProyectoRepository;
import com.backend.repositories.UsuarioRepository;
import com.backend.services.impl.CancionServiceImpl;
import com.backend.util.CancionServiceDataTestUtils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CancionServiceTest {
	@InjectMocks
	private CancionServiceImpl cancionService;

	@Mock
	private ProyectoRepository proyectoRepository;

	@Mock
	private ArtistaRepository artistaRepository;

	@Mock
	private CancionRepository cancionRepository;

	@Mock
	private CreditoRepository creditoRepository;

	@Mock
	private UsuarioRepository usuarioRepository;

	@BeforeEach
    public void init() {
		// Empty for now
    }

	@Test
	public void shouldThrowCancionNotFoundWhenCancionIdDoesNotExist() {
		Long cancionId = 1L;

		Mockito.when(cancionRepository.findById(anyLong())).thenReturn(Optional.empty());

		CancionNotFoundException cancionNotFoundException = assertThrows(CancionNotFoundException.class,
			() -> cancionService.getCancionId(cancionId));

		assertEquals("Cancion not found.", cancionNotFoundException.getMessage());
		Mockito.verify(cancionRepository).findById(anyLong());
	}

	@Test
	public void shouldThrowProyectoNotFoundWhenProyectoIdDoesNotExist() {
		CreateCancionDto mockCreateCancion = CancionServiceDataTestUtils.getMockCreateCancionDto();
		mockCreateCancion.setProyectoId(1L);

		Mockito.when(proyectoRepository.findById(anyLong())).thenReturn(Optional.empty());

		ProyectoNotFoundException proyectoNotFoundException = assertThrows(ProyectoNotFoundException.class,
			() -> cancionService.createCancion(mockCreateCancion));

		assertEquals("Proyecto not found.", proyectoNotFoundException.getMessage());
		Mockito.verify(proyectoRepository).findById(anyLong());
	}

	@Test
	public void shouldThrowIncorrectCancionWhenCancionWithSameNameTriesToBeCreated() {
		Proyecto mockProyecto = CancionServiceDataTestUtils.getMockProyecto();
		Cancion mockCancion = CancionServiceDataTestUtils.getMockCancion();
		mockProyecto.getCanciones().add(mockCancion);
		mockCancion.setProyecto(mockProyecto);

		Mockito.when(proyectoRepository.findById(anyLong())).thenReturn(Optional.of(mockProyecto));
		Mockito.when(cancionRepository.findByProyectoId(anyLong())).thenReturn(mockProyecto.getCanciones());

		CreateCancionDto mockCreateCancion = CancionServiceDataTestUtils.getMockCreateCancionDto();
		mockCreateCancion.setProyectoId(mockProyecto.getId());

		IncorrectCancionException incorrectCancionException = assertThrows(IncorrectCancionException.class,
			() -> cancionService.createCancion(mockCreateCancion));

		assertEquals("Cancion with same name already on Proyecto.", incorrectCancionException.getMessage());
		Mockito.verify(proyectoRepository).findById(anyLong());
		Mockito.verify(cancionRepository).findByProyectoId(anyLong());
	}

	@Test
	public void shouldThrowArtistaNotFoundWhenArtistaIdDoesNotExist() {
		CreateCancionProyectoDto mockCreateCancionProyecto = CancionServiceDataTestUtils.getMockCreateCancionProyectoDto();
		mockCreateCancionProyecto.setArtistaId(1L);

		Mockito.when(artistaRepository.findById(anyLong())).thenReturn(Optional.empty());

		ArtistaNotFoundException artistaNotFoundException = assertThrows(ArtistaNotFoundException.class,
			() -> cancionService.createCancionProyecto(mockCreateCancionProyecto));

		assertEquals("Artista not found.", artistaNotFoundException.getMessage());
		Mockito.verify(artistaRepository).findById(anyLong());
	}

	@Test
	public void shouldThrowUsuarioNotFoundWhenUsuarioIdDoesNotExist() {
		Long usuarioId = 1L, cancionId = 1L;

		Mockito.when(usuarioRepository.findById(anyLong())).thenReturn(Optional.empty());

		UsuarioNotFoundException usuarioNotFoundException = assertThrows(UsuarioNotFoundException.class,
			() -> cancionService.createReproduccion(usuarioId, cancionId));
			
		assertEquals("Usuario not found.", usuarioNotFoundException.getMessage());
		Mockito.verify(usuarioRepository).findById(anyLong());
	}


}

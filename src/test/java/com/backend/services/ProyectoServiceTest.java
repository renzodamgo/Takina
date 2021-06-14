package com.backend.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

import java.util.Optional;

import com.backend.dtos.creates.CreateProyectoDto;
import com.backend.entities.Artista;
import com.backend.entities.Proyecto;
import com.backend.repositories.ArtistaRepository;
import com.backend.repositories.ProyectoRepository;
import com.backend.services.impl.ProyectoServiceImpl;
import com.backend.util.ProyectoServiceDataTestUtils;
import com.backend.exceptions.ArtistaNotFoundException;
import com.backend.exceptions.IncorrectProyectoException;
import com.backend.exceptions.ProyectoNotFoundException;

import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

@ExtendWith(SpringExtension.class)
public class ProyectoServiceTest {
	@InjectMocks
	private ProyectoServiceImpl proyectoService;

	@Mock
	private ArtistaRepository artistaRepository;

	@Mock
	private ProyectoRepository proyectoRepository;

	@BeforeEach
    public void init() {
        //Artista mockArtista = ProyectoServiceDataTestUtils.getMockArtista();
		//Mockito.doReturn(Optional.of(mockArtista)).when(artistaRepository).findById(anyLong());
    }

	@Test
	public void shouldThrowIncorrectProyectoWhenProyectoWithSameNameTriesToBeCreated() {
		Artista mockArtista = ProyectoServiceDataTestUtils.getMockArtista();
		Proyecto mockProyecto = ProyectoServiceDataTestUtils.getMockProyecto();
		mockProyecto.setArtista(mockArtista);
		mockArtista.getProyectos().add(mockProyecto);

		CreateProyectoDto mockCreateProyecto = ProyectoServiceDataTestUtils.getMockCreateProyectoDto();
		mockCreateProyecto.setArtistaId(mockArtista.getId());

		Mockito.when(artistaRepository.findById(anyLong())).thenReturn(Optional.of(mockArtista));
		Mockito.when(proyectoRepository.findByArtistaId(anyLong())).thenReturn(mockArtista.getProyectos());

		IncorrectProyectoException incorrectException = assertThrows(IncorrectProyectoException.class,
			() -> proyectoService.createProyecto(mockCreateProyecto));
		
		assertEquals("Proyecto with same name already exists.",incorrectException.getMessage());
		Mockito.verify(artistaRepository).findById(anyLong());
		Mockito.verify(proyectoRepository).findByArtistaId(anyLong());
	}

	@Test
	public void shouldThrowArtistaNotFoundWhenArtistaIdDoesNotExist() {
		CreateProyectoDto mockCreateProyecto = ProyectoServiceDataTestUtils.getMockCreateProyectoDto();
		mockCreateProyecto.setArtistaId(1L);

		Mockito.when(artistaRepository.findById(anyLong())).thenReturn(Optional.empty());

		ArtistaNotFoundException artistaNotFoundException = assertThrows(ArtistaNotFoundException.class,
			() -> proyectoService.createProyecto(mockCreateProyecto));

		assertEquals("Artista not found.", artistaNotFoundException.getMessage());
		Mockito.verify(artistaRepository).findById(anyLong());
	}

	@Test
	public void shouldThrowProyectoNotFoundWhenProyectoIdDoesNotExist(){
		Long proyectoId = 1L;

		Mockito.when(proyectoRepository.findById(anyLong())).thenReturn(Optional.empty());

		ProyectoNotFoundException proyectoNotFoundException = assertThrows(ProyectoNotFoundException.class,
			() -> proyectoService.getProyectoById(proyectoId));

		assertEquals("Proyecto not found.", proyectoNotFoundException.getMessage());
		Mockito.verify(proyectoRepository).findById(anyLong());
	}

}

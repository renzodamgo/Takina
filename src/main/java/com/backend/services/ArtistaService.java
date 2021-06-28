package com.backend.services;

import java.util.List;

import com.backend.dtos.ArtistaDto;
import com.backend.dtos.EstadisticaDto;
import com.backend.dtos.SeguidorDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.dtos.edits.EditArtistaDto;
import com.backend.exceptions.TakinaException;

public interface ArtistaService {
	ArtistaDto createArtista(CreateArtistaDto createArtistaDto) throws TakinaException;
	ArtistaDto editArtista(EditArtistaDto editArtistaDto) throws TakinaException;
	ArtistaDto getArtista(Long Id) throws TakinaException;
	ArtistaDto getArtistaNombre(String nombre) throws TakinaException;
	ArtistaDto giveAdministrador(Long artistaId, Long usuarioId, Integer nivelInt) throws TakinaException;
	EstadisticaDto getSeguidoresByIdAndDate(Long artistaId, Integer indice) throws TakinaException;
	List<ArtistaDto> getArtistas() throws TakinaException;
	List<ArtistaDto> getArtistasByAdministradorUsuarioId(Long usuarioId) throws TakinaException;
	List<ArtistaDto> getArtistasByDepartamento(String departamento) throws TakinaException;
	List<ArtistaDto> getArtistasByGenero(String genero) throws TakinaException;
	List<ArtistaDto> getArtistasByNombre(String nombre) throws TakinaException;
	SeguidorDto createSeguidor(Long usuarioId, Long artistaId) throws TakinaException;
	void deleteArtistaById(Long artistaId) throws TakinaException;
	void deleteSeguidor(Long usuarioId, Long artistaId) throws TakinaException;
}

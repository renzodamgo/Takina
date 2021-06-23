package com.backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.ArtistaDto;
import com.backend.dtos.EstadisticaDto;
import com.backend.dtos.SeguidorDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.dtos.edits.EditArtistaDto;
import com.backend.entities.Administrador;
import com.backend.entities.Artista;
import com.backend.entities.Seguidor;
import com.backend.entities.Usuario;
import com.backend.exceptions.ArtistaNotFoundException;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.TakinaException;
import com.backend.exceptions.UsuarioNotFoundException;
import com.backend.repositories.AdministradorRepository;
import com.backend.repositories.ArtistaRepository;
import com.backend.repositories.SeguidorRepository;
import com.backend.repositories.UsuarioRepository;
import com.backend.services.ArtistaService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistaServiceImpl implements ArtistaService {

	@Autowired
	private ArtistaRepository artistaRepository;
	private static final ModelMapper modelMapper = new ModelMapper();

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private AdministradorRepository administradorRepository;

	@Autowired
	private SeguidorRepository seguidorRepository;

	@Override
	public ArtistaDto getArtista(Long artistaId) throws TakinaException{
		return modelMapper.map(getArtistaEntity(artistaId), ArtistaDto.class);
	}

	private Artista getArtistaEntity(Long artistaId) throws TakinaException {
		return artistaRepository.findById(artistaId)
				.orElseThrow(()-> new ArtistaNotFoundException("Artista not found."));
	}

	public ArtistaDto getArtistaNombre(String nombre) throws TakinaException {
		return modelMapper.map(getArtistaEntityNombre(nombre), ArtistaDto.class);
	}

	private Artista getArtistaEntityNombre(String nombre) throws TakinaException {
		return artistaRepository.findByNombre(nombre)
				.orElseThrow(()-> new ArtistaNotFoundException("Artista not found."));
	}

	@Override
	public List<ArtistaDto> getArtistas() throws TakinaException {
		List<Artista> artistaEntity = artistaRepository.findAll();
		return artistaEntity.stream().map(artista -> modelMapper.map(artista, ArtistaDto.class)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public ArtistaDto createArtista(CreateArtistaDto createArtistaDto) throws TakinaException {
		Artista artista = new Artista();
		artista.setNombre(createArtistaDto.getNombre());
		artista.setBiografia(createArtistaDto.getBiografia());
		artista.setFotoPerfil(createArtistaDto.getFotoPerfil());
		artista.setFotoPortada(createArtistaDto.getFotoPortada());
		artista.setDepartamento(createArtistaDto.getDepartamento());
		artista.setGenero(createArtistaDto.getGenero());

		try {
			artista = artistaRepository.save(artista);
		} catch (Exception ex){
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","ARTISTA_NOT_CREATED");
		}

		Usuario usuario = usuarioRepository.findById(createArtistaDto.getUsuarioId())
				.orElseThrow(()->new UsuarioNotFoundException("Usuario not found."));

		Administrador administrador = new Administrador();
		administrador.setArtista(artista);
		administrador.setUsuario(usuario);
		administrador.setFechaRegistro(LocalDateTime.now());
		
		try {
			administrador = administradorRepository.save(administrador);
		} catch (Exception ex){
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","ARTISTA_NOT_CREATED");
		}

		return modelMapper.map(getArtistaEntity(artista.getId()),ArtistaDto.class);
	}

	@Override
	public ArtistaDto giveAdministrador(Long artistaId, Long usuarioId, Integer nivelInt) throws TakinaException {
		Optional<Administrador> validation = administradorRepository.findByArtistaIdAndUsuarioId(artistaId, usuarioId);

		if (validation.isPresent()) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","IS_ADMINISTRATOR_ALREADY");
		}

		Artista artista = getArtistaEntity(artistaId);

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(()->new UsuarioNotFoundException("Usuario not found."));

		String nivel;
		switch(nivelInt) {
			case 0:
				nivel = "Administrador"; break;
			case 1:
				nivel = "Moderador"; break;
			case 2:
				nivel = "Ayudante"; break;
			case 3:
				nivel = "Publicitario"; break;
			default:
				throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","LEVEL_NOT_VALID");
		  }

		Administrador administrador = new Administrador();
		administrador.setArtista(artista);
		administrador.setUsuario(usuario);
		administrador.setNivel(nivel);
		administrador.setFechaRegistro(LocalDateTime.now());

		usuario.getAdministradores().add(administrador);
		artista.getAdministradores().add(administrador);

		try {
			administrador = administradorRepository.save(administrador);
		}catch (Exception ex){
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","ARTISTA_NOT_CREATED");
		}
		return modelMapper.map(getArtistaEntity(artista.getId()),ArtistaDto.class);
	}
	
	@Override
	public List<ArtistaDto> getArtistasByNombre(String nombre) throws TakinaException {
		List<Artista> results = artistaRepository.findByNombreContainingIgnoreCase(nombre);
		return results.stream().map(artista -> modelMapper.map(artista,ArtistaDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ArtistaDto> getArtistasByGenero(String genero) throws TakinaException {
		List<Artista> results = artistaRepository.findByGeneroContainingIgnoreCase(genero);
		return results.stream().map(artista -> modelMapper.map(artista,ArtistaDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<ArtistaDto> getArtistasByDepartamento(String departamento) throws TakinaException {
		List<Artista> results = artistaRepository.findByDepartamentoContainingIgnoreCase(departamento);
		return results.stream().map(artista -> modelMapper.map(artista,ArtistaDto.class)).collect(Collectors.toList());
	}

	@Override
	public ArtistaDto editArtista(EditArtistaDto editArtistaDto) throws TakinaException {
		Artista artista = getArtistaEntity(editArtistaDto.getId());

		artista.setNombre(editArtistaDto.getNombre());
		artista.setFotoPerfil(editArtistaDto.getFotoPerfil());
		artista.setFotoPortada(editArtistaDto.getFotoPortada());
		artista.setBiografia(editArtistaDto.getBiografia());
		artista.setDepartamento(editArtistaDto.getDepartamento());
		artista.setGenero(editArtistaDto.getGenero());

		try {
			artista = artistaRepository.save(artista);
		} catch(Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","ARTIST_NOT_EDITED");
		}

		return modelMapper.map(artista, ArtistaDto.class);
	}

	@Override
	public EstadisticaDto getSeguidoresByIdAndDate(Long artistaId, Integer indice) throws TakinaException {
		Artista artista = getArtistaEntity(artistaId);

		EstadisticaDto estadistica = new EstadisticaDto();
		estadistica.setIndice(indice);
		estadistica.setCantidad(
			seguidorRepository.countByArtistaIdAndGreaterThanFecha(
								artista.getId(),
								LocalDateTime.now().minusMonths(indice).minusHours(1)));

		return estadistica;
	}

	@Transactional
	@Override
	public SeguidorDto createSeguidor(Long usuarioId, Long artistaId) throws TakinaException {
		Optional<Seguidor> validacion = seguidorRepository.findByUsuarioIdAndArtistaId(usuarioId, artistaId);
		
		if (validacion.isPresent()) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_CREATED");
		}

		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(()->new UsuarioNotFoundException("Usuario not found."));

		Artista artista = getArtistaEntity(artistaId);

		artista.setTotalSeguidores(artista.getTotalSeguidores()+1);

		Seguidor seguidor = new Seguidor();
		seguidor.setUsuario(usuario);
		seguidor.setArtista(artista);
		seguidor.setFecha(LocalDateTime.now());

		try {
			seguidor = seguidorRepository.save(seguidor);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_CREATED");
		}

		return modelMapper.map(seguidor,SeguidorDto.class);
	}

	@Override
	public void deleteSeguidor(Long usuarioId, Long artistaId) throws TakinaException {
		Optional<Seguidor> validacion = seguidorRepository.findByUsuarioIdAndArtistaId(usuarioId, artistaId);
		if (validacion.isPresent()) {
			seguidorRepository.deleteById(validacion.get().getId());

			Artista artista = getArtistaEntity(artistaId);
			artista.setTotalSeguidores(artista.getTotalSeguidores()-1);
		} else {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","SEGUIDOR_NOT_FOUND");
		}
	}

	@Override
	public List<ArtistaDto> getArtistasByAdministradorUsuarioId(Long usuarioId) {
		List<Administrador> administradores = administradorRepository.findByUsuarioId(usuarioId);
		List<Artista> artistas = new ArrayList<>();
		for (Administrador adm: administradores) {
			artistas.add(adm.getArtista());
		}

		return artistas.stream().map(artista -> modelMapper
					.map(artista,ArtistaDto.class))
					.collect(Collectors.toList());
	}
}

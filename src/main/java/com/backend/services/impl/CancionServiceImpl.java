package com.backend.services.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.CancionDto;
import com.backend.dtos.EstadisticaDto;
import com.backend.dtos.ReproduccionDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.dtos.creates.CreateCancionProyectoDto;
import com.backend.dtos.creates.CreateCreditoDto;
import com.backend.entities.Artista;
import com.backend.entities.Cancion;
import com.backend.entities.Credito;
import com.backend.entities.Listado;
import com.backend.entities.Proyecto;
import com.backend.entities.Reproduccion;
import com.backend.entities.Usuario;
import com.backend.exceptions.ArtistaNotFoundException;
import com.backend.exceptions.CancionNotFoundException;
import com.backend.exceptions.IncorrectCancionException;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.ProyectoNotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.exceptions.UsuarioNotFoundException;
import com.backend.repositories.ArtistaRepository;
import com.backend.repositories.CancionRepository;
import com.backend.repositories.CreditoRepository;
import com.backend.repositories.ListadoRepository;
import com.backend.repositories.ProyectoRepository;
import com.backend.repositories.ReproduccionRepository;
import com.backend.repositories.UsuarioRepository;
import com.backend.services.CancionService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CancionServiceImpl implements CancionService {
	@Autowired
	private ProyectoRepository proyectoRepository;

	@Autowired
	private ArtistaRepository artistaRepository;

	@Autowired
	private CancionRepository cancionRepository;

	@Autowired
	private CreditoRepository creditoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ReproduccionRepository reproduccionRepository;

	@Autowired
	private ListadoRepository listadoRepository;

	private static final ModelMapper modelMapper = new ModelMapper();

	@Override
	public CancionDto getCancionId(Long cancionId) throws TakinaException {
		return modelMapper.map(getCancionEntity(cancionId),CancionDto.class);

	}

	private Cancion getCancionEntity(Long cancionId) throws TakinaException {
		return cancionRepository.findById(cancionId)
				.orElseThrow(()-> new CancionNotFoundException("Cancion not found."));
	}

	@Override
	public List<CancionDto> getCanciones() throws TakinaException {
		List<Cancion> cancionesEntity = cancionRepository.findAll();
		return cancionesEntity.stream().map(cancion -> modelMapper.map(cancion, CancionDto.class)).collect(Collectors.toList());
	}

	@Override
	public CancionDto getCancionNombre(String nombre) throws TakinaException {
		return modelMapper.map(getCancionEntityNombre(nombre),CancionDto.class);
	}

	private Cancion getCancionEntityNombre(String nombre) throws TakinaException {
		return cancionRepository.findByNombre(nombre)
				.orElseThrow(()-> new CancionNotFoundException("Cancion not found."));
	}

	@Transactional
	@Override
	public CancionDto createCancion(CreateCancionDto createCancionDto) throws TakinaException {
		Proyecto proyecto = proyectoRepository.findById(createCancionDto.getProyectoId())
				.orElseThrow(()->new ProyectoNotFoundException("Proyecto not found."));

		List<Cancion> validacion = cancionRepository.findByProyectoId(createCancionDto.getProyectoId());
		for (Cancion c : validacion) {
			if (c.getNombre().equals(createCancionDto.getNombre())) {
				throw new IncorrectCancionException("Cancion with same name already on Proyecto.");
			}
		}

		Cancion cancion = new Cancion();
		cancion.setNombre(createCancionDto.getNombre());
		cancion.setAudio(createCancionDto.getAudio());
		cancion.setDuracion(createCancionDto.getDuracion());
	
		cancion.setFotoPortada(proyecto.getFotoPortada());
		cancion.setLanzamiento(proyecto.getLanzamiento());
		cancion.setGenero(proyecto.getGenero());
		cancion.setProyecto(proyecto);
		cancion.setTrack(proyecto.getNumCanciones()+1);

		try {
			cancion = cancionRepository.save(cancion);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CANCION_NOT_CREATED");
		}
		
		proyecto.setNumCanciones(proyecto.getNumCanciones()+1);
		proyecto.setDuracion(proyecto.getDuracion()+cancion.getDuracion());
		
		Credito credito = new Credito();
		credito.setArtista(proyecto.getArtista());
		credito.setCancion(cancion);

		try {
			credito = creditoRepository.save(credito);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CREDITO_NOT_CREATED");
		}
		
		return modelMapper.map(getCancionEntity(cancion.getId()),CancionDto.class);
	}

	@Transactional
	@Override
	public CancionDto createCancionProyecto(CreateCancionProyectoDto createCancionProyectoDto) throws TakinaException {
		Artista artista = artistaRepository.findById(createCancionProyectoDto.getArtistaId())
				.orElseThrow(()->new ArtistaNotFoundException("Artista not found."));

		Proyecto proyecto = new Proyecto();
		proyecto.setNombre(createCancionProyectoDto.getNombre());
		proyecto.setDuracion(createCancionProyectoDto.getDuracion());
		proyecto.setTipo("Sencillo");
		proyecto.setDescripcion(createCancionProyectoDto.getDescripcion());
		proyecto.setLanzamiento(createCancionProyectoDto.getLanzamiento());
		proyecto.setNumCanciones(1);
		proyecto.setDiscografica(createCancionProyectoDto.getDiscografica());
		proyecto.setArtista(artista);
		proyecto.setFotoPortada(createCancionProyectoDto.getFotoPortada());
		proyecto.setGenero(artista.getGenero());
		proyecto.setFecha(LocalDateTime.now());

		try {
			proyecto = proyectoRepository.save(proyecto);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","PROYECTO_NOT_CREATED");
		}
		
		Cancion cancion = new Cancion();
		cancion.setNombre(createCancionProyectoDto.getNombre());
		cancion.setAudio(createCancionProyectoDto.getAudio());
		cancion.setDuracion(createCancionProyectoDto.getDuracion());
		cancion.setFotoPortada(proyecto.getFotoPortada());
		cancion.setLanzamiento(proyecto.getLanzamiento());
		cancion.setGenero(proyecto.getGenero());
		cancion.setProyecto(proyecto);
		cancion.setTrack(1);
		
		try {
			cancion = cancionRepository.save(cancion);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CANCION_NOT_CREATED");
		}

		Credito credito = new Credito();
		credito.setArtista(proyecto.getArtista());
		credito.setCancion(cancion);

		try {
			credito = creditoRepository.save(credito);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CREDITO_NOT_CREATED");
		}
	
		return modelMapper.map(getCancionEntity(credito.getCancion().getId()),CancionDto.class);
	}
	
	@Override
	public List<CancionDto> getCancionesByNombre(String nombre) throws TakinaException {
		List<Cancion> results = cancionRepository.findByNombreContainingIgnoreCase(nombre);
		return results.stream().map(cancion -> modelMapper.map(cancion,CancionDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<CancionDto> getCancionesByGeneroMusical(String generoMusical) throws TakinaException {
		List<Cancion> results = cancionRepository.findByGeneroContainingIgnoreCase(generoMusical);
		return results.stream().map(cancion -> modelMapper.map(cancion,CancionDto.class)).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void deleteCancionById(Long cancionId) throws TakinaException {
		Optional<Cancion> cancion = cancionRepository.findById(cancionId);

		if (cancion.isPresent()) {
			Proyecto proyecto = cancion.get().getProyecto();
			proyecto.setNumCanciones(proyecto.getNumCanciones()-1);
			proyecto.setDuracion(proyecto.getDuracion()-cancion.get().getDuracion());
			proyecto = proyectoRepository.save(proyecto);

			cancionRepository.deleteById(cancionId);

			List<Cancion> canciones = cancionRepository.findByProyectoId(proyecto.getId());
			for(int i = 0; i < canciones.size(); i++) {
				Cancion can = canciones.get(i);
				can.setTrack(i+1);
				can = cancionRepository.save(can);
			}
		} else {
			throw new CancionNotFoundException("Cancion not found.");
		}
	}

	@Override
	public CancionDto createCredito(CreateCreditoDto createCreditoDto) throws TakinaException {
		Artista artista = artistaRepository.findById(createCreditoDto.getArtistaId())
				.orElseThrow(()->new ArtistaNotFoundException("Artista not found."));

		Cancion cancion = getCancionEntity(createCreditoDto.getCancionId());

		Credito credito = new Credito();
		credito.setArtista(artista);
		credito.setCancion(cancion);
		credito.setDescripcion(createCreditoDto.getDescripcion());

		try {
			credito = creditoRepository.save(credito);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","CREDITO_NOT_CREATED");
		}

		return modelMapper.map(getCancionEntity(credito.getCancion().getId()),CancionDto.class);
	}

	@Transactional
	@Override
	public ReproduccionDto createReproduccion(Long usuarioId, Long cancionId) throws TakinaException {
		Usuario usuario = usuarioRepository.findById(usuarioId)
				.orElseThrow(()->new UsuarioNotFoundException("Usuario not found."));

		Cancion cancion = getCancionEntity(cancionId);

		Reproduccion reproduccion = new Reproduccion();
		reproduccion.setUsuario(usuario);
		reproduccion.setCancion(cancion);
		reproduccion.setFecha(LocalDateTime.now());

		try {
			reproduccion = reproduccionRepository.save(reproduccion);
		} catch (Exception ex) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","REPRODUCCION_NOT_CREATED");
		}

		Artista artista = cancion.getProyecto().getArtista();
		artista.setTotalReproducciones(artista.getTotalReproducciones()+1);
		artista = artistaRepository.save(artista);

		return modelMapper.map(reproduccion,ReproduccionDto.class);
	}

	@Override
	public List<CancionDto> getCancionesByProyectoId(Long proyectoId) throws TakinaException {
		List<Cancion> canciones = cancionRepository.findByProyectoId(proyectoId);

		return canciones.stream().map(cancion -> modelMapper.map(cancion, CancionDto.class))
			.collect(Collectors.toList());
	}

	@Override
	public List<CancionDto> getCancionesByPlaylistId(Long playlistId) throws TakinaException {
		List<Listado> listados = listadoRepository.findByPlaylistId(playlistId);
		
		List<Cancion> canciones = new ArrayList<>();
		for (Listado lis : listados) {
			canciones.add(lis.getCancion());
		}

		return canciones.stream().map(cancion -> modelMapper.map(cancion, CancionDto.class))
						.collect(Collectors.toList());
	}

	@Override
	public List<ReproduccionDto> getHistorial(Long usuarioId) throws TakinaException {
		List<Reproduccion> reproducciones = reproduccionRepository.findByUsuarioIdOrderByFecha(usuarioId);

		List<Reproduccion> historial = new ArrayList<>();
		Integer cantidad = 20;
		for(int i = 0; i < reproducciones.size(); i++){
			if(historial.size() == cantidad) break;
			
			Boolean found = false;
			for(int j = 0; j < historial.size(); j++) {
				if(reproducciones.get(i).getUsuario().getId() == historial.get(j).getUsuario().getId() &&
					reproducciones.get(i).getCancion().getId() == historial.get(j).getCancion().getId()) {
					found = true;
					break;
				}
			}

			if(!found) historial.add(reproducciones.get(i));
		}
		
		return historial.stream().map(reproduccion -> modelMapper
						.map(reproduccion,ReproduccionDto.class))
						.collect(Collectors.toList());
	}

	@Override
	public EstadisticaDto getReproduccionesByArtistaIdAndDate(Long artistaId, Integer indice) throws TakinaException {
		Artista artista = artistaRepository.findById(artistaId)
				.orElseThrow(()->new ArtistaNotFoundException("Artista not found."));

		EstadisticaDto estadistica = new EstadisticaDto();
		estadistica.setIndice(indice);
		estadistica.setCantidad(
			reproduccionRepository.countByArtistaIdAndGreaterThanFecha(
								artista.getId(),
								LocalDateTime.now().minusMonths(indice).minusHours(1)));
		return new EstadisticaDto();
	}
}

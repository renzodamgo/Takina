package com.backend.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.entities.Usuario;
import com.backend.exceptions.InternalServerErrorException;
import com.backend.exceptions.NotFoundException;
import com.backend.exceptions.TakinaException;
import com.backend.repositories.UsuarioRepository;
import com.backend.services.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {


    @Autowired
    private UsuarioRepository usuarioRepository;
    private static final ModelMapper modelMapper = new ModelMapper();

    // -------------------------------------------------------
    @Override
    public UsuarioDto getUsuarioId(Long UsuarioId) throws TakinaException {
        return modelMapper.map(getUsuarioEntity(UsuarioId),UsuarioDto.class);

    }

    private Usuario getUsuarioEntity(Long UsuarioId) throws TakinaException {
        return usuarioRepository.findById(UsuarioId)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
    }

    private Usuario getUsuarioLogin(String login) throws TakinaException {
        return usuarioRepository.findByApodoOrCorreo(login,login)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
    }


    // -------------------------------------------------------
    @Override
    public List<UsuarioDto> getUsuarios() throws TakinaException {
        List<Usuario> usuariosEntity = usuarioRepository.findAll();
        return usuariosEntity.stream().map(Usuario -> modelMapper.map(Usuario, UsuarioDto.class)).collect(Collectors.toList());
    }

    // -------------------------------------------------------
    @Override
    public UsuarioDto getUsuarioNombre(String nombre) throws TakinaException {
        return modelMapper.map(getUsuarioEntityNombre(nombre),UsuarioDto.class);
    }

    private Usuario getUsuarioEntityNombre(String nombre) throws TakinaException {
        return usuarioRepository.findByNombre(nombre)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
    }

	// -------------------------------------------------------
	@Override
	public UsuarioDto getUsuarioApodo(String apodo) throws TakinaException {
	 return modelMapper.map(getUsuarioEntityApodo(apodo),UsuarioDto.class);
	}
 
	private Usuario getUsuarioEntityApodo(String apodo) throws TakinaException {
       return usuarioRepository.findByApodo(apodo)
               .orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
    }

	// -------------------------------------------------------
	@Override
	public UsuarioDto getUsuarioCorreo(String correo) throws TakinaException {
	 return modelMapper.map(getUsuarioEntityCorreo(correo),UsuarioDto.class);
	}
 
	private Usuario getUsuarioEntityCorreo(String correo) throws TakinaException {
        return usuarioRepository.findByCorreo(correo)
                .orElseThrow(()-> new NotFoundException("NOTFOUND-404","Usuario_NOTFOUND-404"));
    }

    // --------------------------------------------------------
    @Transactional
    @Override
    public UsuarioDto createUsuario(CreateUsuarioDto createUsuarioDto) throws TakinaException {
        Usuario Usuario = new Usuario();
		Usuario.setApodo(createUsuarioDto.getApodo());
        Usuario.setNombre(createUsuarioDto.getNombre());
        Usuario.setPassword(createUsuarioDto.getPassword());
        Usuario.setCorreo(createUsuarioDto.getCorreo());
        Usuario.setFotoPerfil(createUsuarioDto.getFotoPerfil());
		Usuario.setUltimoIngreso(LocalDateTime.now());
		
        try {
            Usuario = usuarioRepository.save(Usuario);
        }catch (Exception ex){
            throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_CREATED");
        }
        return modelMapper.map(getUsuarioEntity(Usuario.getId()),UsuarioDto.class);
    }

	// --------------------------------------------------------
	// Busqueda de usuarios por nombre
	@Override
    public List<UsuarioDto> getUsuariosByNombre(String nombre) throws TakinaException{
        List<Usuario> results = usuarioRepository.findByNombreContainingIgnoreCase(nombre);
        return results.stream().map(usuario -> modelMapper.map(usuario,UsuarioDto.class)).collect(Collectors.toList());
    }

	// --------------------------------------------------------
	// Login de usuario usando contraseña
	@Override
    public UsuarioDto loginUsuarioByApodoOrCorreoUsingPassword(String login, String password) throws TakinaException {
		Boolean encontrado = false;
		Usuario Usuario = getUsuarioLogin(login);
		
		if (Usuario.getPassword().equals(password)) {
			encontrado = true;
            System.out.println("Usuario ha sido encontrado");
		}


		if (!encontrado) {
			throw new InternalServerErrorException("INTERNAL_SERVER_ERROR","USUARIO_NOT_FOUND");
		}

		System.out.println("RETURN:");
		return modelMapper.map(getUsuarioEntity(Usuario.getId()),UsuarioDto.class);
    }
}

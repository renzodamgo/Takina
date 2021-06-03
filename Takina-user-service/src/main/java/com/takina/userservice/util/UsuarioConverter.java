package com.takina.userservice.util;

import com.takina.userservice.dto.UsuarioDto;
import com.takina.userservice.dto.edit.EditUsuarioDto;
import com.takina.userservice.entities.Usuario;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioDto convertUsuarioToDto(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioDto.class);
    }

    public Usuario convertUsuarioToEntity(UsuarioDto usuarioDto) {
        return modelMapper.map(usuarioDto, Usuario.class);
    }

    public Usuario map(EditUsuarioDto usuarioToUpdate, Usuario usuario) {
        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        modelMapper.map(usuarioToUpdate, usuario);
        return usuario;
    }
}
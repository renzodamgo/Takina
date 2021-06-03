package com.takina.userservice.util;

import com.takina.userservice.dto.PlaylistDto;
import com.takina.userservice.dto.UsuarioDto;
import com.takina.userservice.dto.edit.EditUsuarioDto;
import com.takina.userservice.entities.Playlist;
import com.takina.userservice.entities.Usuario;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlaylistConverter {
    @Autowired
    private ModelMapper modelMapper;

    public PlaylistDto convertPlaylistToDto(Playlist playlist) {
        return modelMapper.map(playlist, PlaylistDto.class);
    }

    public Playlist convertPlaylistToEntity(PlaylistDto playlistDto) {
        return modelMapper.map(playlistDto, Playlist.class);
    }
}

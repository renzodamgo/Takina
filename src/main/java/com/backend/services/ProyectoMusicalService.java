
package com.backend.services;

import java.util.List;

import com.backend.dtos.ProyectoMusicalDto;
import com.backend.dtos.creates.CreateProyectoMusicalDto;
import com.backend.exceptions.TakinaException;

public interface ProyectoMusicalService {
    ProyectoMusicalDto getProyectoMusicalById(Long cancionId) throws TakinaException;
	ProyectoMusicalDto getProyectoMusicalNombre(String nombre) throws TakinaException;
    List<ProyectoMusicalDto> getProyectosMusicales() throws TakinaException;

    // US022 - Como usuario Quiero buscar un proyecto musical según una porción de texto Para revisar la produccion musical en ese instante
    List<ProyectoMusicalDto> getProyectosMusicalesByNombre(String nombre) throws TakinaException;

    // US014 - Como usuario administrador de un perfil de artista Quiero publicar proyectos musicales a la plataforma Para promocionar mi música públicamente
    ProyectoMusicalDto createProyectoMusical(CreateProyectoMusicalDto createProyectoMusicalDto) throws TakinaException;

}
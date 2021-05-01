package com.backend.services;

import java.util.List;

import com.backend.dtos.MercanciaDto;
import com.backend.dtos.creates.CreateMercanciaDto;
import com.backend.exceptions.TakinaException;

public interface MercanciaService {
    MercanciaDto getMercanciaId(Long mercanciaId) throws TakinaException;

    List<MercanciaDto> getMercancia() throws TakinaException;
    
    // US015 Como usuario administrador de un perfil de artista Quiero publicar un enlace del catálogo de mis mercancías Para poder obtener remuneración a través de la venta
    MercanciaDto createMercania(CreateMercanciaDto createMercanciaDto) throws TakinaException;


}

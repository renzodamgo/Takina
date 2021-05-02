package com.backend.services;

import java.util.List;

import com.backend.dtos.MercanciaDto;
import com.backend.dtos.creates.CreateMercanciaDto;
import com.backend.exceptions.TakinaException;

public interface MercanciaService {
	// Obtener Mercancia por ID
    MercanciaDto getMercanciaId(Long mercanciaId) throws TakinaException;
	// Obtener todas las mercancias
    List<MercanciaDto> getMercancias() throws TakinaException;
    
    // US015 Como usuario administrador de un perfil de artista Quiero publicar un articulo de mi mercancía Para que los usuarios sepan que esta en venta y obtener remuneración a través de la venta
    MercanciaDto createMercancia(CreateMercanciaDto createMercanciaDto) throws TakinaException;


}

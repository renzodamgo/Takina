package com.backend.services;

import java.util.List;

import com.backend.dtos.MercanciaDto;
import com.backend.dtos.creates.CreateMercanciaDto;
import com.backend.exceptions.TakinaException;

public interface MercanciaService {

	MercanciaDto getMercanciaId(Long mercanciaId) throws TakinaException;

	List<MercanciaDto> getMercancias() throws TakinaException;
	
	MercanciaDto createMercancia(CreateMercanciaDto createMercanciaDto) throws TakinaException;

}

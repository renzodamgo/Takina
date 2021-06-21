package com.backend.services;

import java.util.List;

import com.backend.dtos.MercanciaDto;
import com.backend.dtos.creates.CreateMercanciaDto;
import com.backend.exceptions.TakinaException;

public interface MercanciaService {
	List<MercanciaDto> getMercancias() throws TakinaException;
	MercanciaDto createMercancia(CreateMercanciaDto createMercanciaDto) throws TakinaException;
	MercanciaDto getMercanciaId(Long mercanciaId) throws TakinaException;
}

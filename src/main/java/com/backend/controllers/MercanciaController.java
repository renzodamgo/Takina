package com.backend.controllers;

import java.util.List;

import com.backend.dtos.MercanciaDto;
import com.backend.dtos.creates.CreateMercanciaDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.MercanciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = "/takina"+"/v1")
public class MercanciaController {
    @Autowired
    private MercanciaService mercanciaService;

	// Crear Mercancia
	@ResponseStatus(HttpStatus.OK)
    @PostMapping("/mercancias/nuevo")
    public TakinaResponse<MercanciaDto> createMercancia(@RequestBody CreateMercanciaDto createMercanciaDto)
            throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                mercanciaService.createMercancia(createMercanciaDto));
    }

	// Mostrar todas las mercancias
	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/mercancias/todos")
    public TakinaResponse<List<MercanciaDto>> getMercancias()
			throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				mercanciaService.getMercancias());
    }

	// Getters
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mercancias/id/{mercanciaId}")
    public TakinaResponse<MercanciaDto> getMercanciaId(@PathVariable Long mercanciaId)
			throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK), "OK",mercanciaService.getMercanciaId(mercanciaId));

    }
    
}

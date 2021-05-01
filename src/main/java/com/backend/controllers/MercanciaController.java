package com.backend.controllers;

import com.backend.dtos.MercanciaDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.MercanciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(path = "/takina"+"/v1")
public class MercanciaController {

    @Autowired
    private MercanciaService mercanciaService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/mercancia/id/{mercanciaId}")
    public TakinaResponse<MercanciaDto> getMercanciaId(@PathVariable Long mercanciaId) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK), "OK",mercanciaService.getMercancia(mercanciaId));

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/artistas")
    public TakinaResponse<ArtistaDto> createArtista(@RequestBody CreateArtistaDto createArtistaDto)
            throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                artistaService.createArtista(createArtistaDto));
    }


    
}

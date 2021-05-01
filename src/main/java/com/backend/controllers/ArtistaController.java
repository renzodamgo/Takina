package com.backend.controllers;

import com.backend.dtos.ArtistaDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.ArtistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/takina"+"/v1")
public class ArtistaController {

    @Autowired
    private ArtistaService artistaService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/artistas/{ArtistaId}")
    public TakinaResponse<ArtistaDto> getArtistaById(@PathVariable Long ArtistaId) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),
                "OK",artistaService.getArtista(ArtistaId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/artistas/{ArtistaNombre}")
    public TakinaResponse<ArtistaDto> getArtistaByNombre(@PathVariable String ArtistaNombre) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),
                "OK",artistaService.getArtistaNombre(ArtistaNombre));

    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/artistas")
    public TakinaResponse<ArtistaDto> createArtista(@RequestBody CreateArtistaDto createArtistaDto)
            throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                artistaService.createArtista(createArtistaDto));
    }

}
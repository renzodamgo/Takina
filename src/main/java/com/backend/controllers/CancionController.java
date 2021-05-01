package com.backend.controllers;

import com.backend.dtos.CancionDto;
import com.backend.dtos.creates.CreateArtistaDto;
import com.backend.dtos.creates.CreateCancionDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.CancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/takina"+"/v1")
public class CancionController {

    @Autowired
    private CancionService cancionService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/canciones/{cancionId}")
    public TakinaResponse<CancionDto> getCancionById(@PathVariable Long cancionId) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),
                "OK",cancionService.getCancionId(cancionId));

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/canciones/{cancionNombre}")
    public TakinaResponse<CancionDto> getCancionByNombre(@PathVariable String cancionNombre) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),
                "OK",cancionService.getCancionNombre(cancionNombre));

    }



    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/canciones")
    public TakinaResponse<CancionDto> createCancion(@RequestBody CreateCancionDto createCancionDto)
            throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                cancionService.createCancion(createCancionDto));
    }

}
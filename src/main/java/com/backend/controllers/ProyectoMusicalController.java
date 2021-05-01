package com.backend.controllers;


import com.backend.dtos.ProyectoMusicalDto;
import com.backend.dtos.creates.CreateProyectoMusicalDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.ProyectoMusicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/takina"+"/v1")
public class ProyectoMusicalController {
    @Autowired
    ProyectoMusicalService proyectoMusicalService;


    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/proyectos")
    public TakinaResponse<ProyectoMusicalDto> createProyectoMusical(@RequestBody CreateProyectoMusicalDto createProyectoMusicalDto)
        throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                proyectoMusicalService.createProyectoMusical(createProyectoMusicalDto));
    }

}

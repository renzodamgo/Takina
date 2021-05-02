package com.backend.controllers;

import com.backend.dtos.ProyectoMusicalDto;
import com.backend.dtos.creates.CreateProyectoMusicalDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.ProyectoMusicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/takina"+"/v1")
public class ProyectoMusicalController {
    @Autowired
    ProyectoMusicalService proyectoMusicalService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/proyectos/nuevo")
    public TakinaResponse<ProyectoMusicalDto> createProyectoMusical(@RequestBody CreateProyectoMusicalDto createProyectoMusicalDto) throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                proyectoMusicalService.createProyectoMusical(createProyectoMusicalDto));
    }

	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/proyectos/todos")
    public TakinaResponse<List<ProyectoMusicalDto>> getProyectosMusicales()
			throws TakinaException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoMusicalService.getProyectosMusicales());
    }

	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/proyectos/id/{proyectoId}")
    public TakinaResponse<ProyectoMusicalDto> getProyectoMusicalById(@PathVariable Long proyectoId)
			throws TakinaException {
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoMusicalService.getProyectoMusicalById(proyectoId));
    }

	@ResponseStatus(HttpStatus.OK)
    @GetMapping("/proyectos/buscar/nombre/{proyectoMusicalNombre}")
    public TakinaResponse<List<ProyectoMusicalDto>> getProyectosMusicalesByNombre(@PathVariable String proyectoMusicalNombre)
			throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
			proyectoMusicalService.getProyectosMusicalesByNombre(proyectoMusicalNombre));
    }

}

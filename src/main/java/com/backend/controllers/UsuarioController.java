package com.backend.controllers;

import com.backend.dtos.UsuarioDto;
import com.backend.dtos.creates.CreateUsuarioDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/takina"+"/v1")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/{usuarioId}")
    public TakinaResponse<UsuarioDto> getUsuarioById(@PathVariable Long usuarioId) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),
                "OK",usuarioService.getUsuarioId(usuarioId));

    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/usuarios/{usuarioNombre}")
    public TakinaResponse<UsuarioDto> getUsuarioByNombre(@PathVariable String usuarioNombre) throws TakinaException {
        return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),
                "OK",usuarioService.getUsuarioNombre(usuarioNombre));

    }



    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/usuarios")
    public TakinaResponse<UsuarioDto> createUsuario(@RequestBody CreateUsuarioDto createUsuarioDto)
            throws TakinaException{
        return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
                usuarioService.createUsuario(createUsuarioDto));
    }
}

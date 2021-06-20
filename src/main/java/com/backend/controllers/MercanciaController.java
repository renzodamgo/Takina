package com.backend.controllers;

import java.util.List;

import com.backend.dtos.MercanciaDto;
import com.backend.dtos.creates.CreateMercanciaDto;
import com.backend.exceptions.TakinaException;
import com.backend.responses.TakinaResponse;
import com.backend.services.MercanciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/takina"+"/mercancias")
public class MercanciaController {
	@Autowired
	private MercanciaService mercanciaService;

	@ResponseStatus(HttpStatus.OK)
	@PostMapping
	public TakinaResponse<MercanciaDto> createMercancia(@RequestBody CreateMercanciaDto createMercanciaDto)
			throws TakinaException{
		return new TakinaResponse<>("Success",String.valueOf(HttpStatus.OK),"OK",
				mercanciaService.createMercancia(createMercanciaDto));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping
	public TakinaResponse<List<MercanciaDto>> getMercancias()
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK),"OK",
				mercanciaService.getMercancias());
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping("/id/{mercanciaId}")
	public TakinaResponse<MercanciaDto> getMercanciaId(@PathVariable Long mercanciaId)
			throws TakinaException {
		return new TakinaResponse<>( "Success",String.valueOf(HttpStatus.OK), "OK",mercanciaService.getMercanciaId(mercanciaId));

	}	
}

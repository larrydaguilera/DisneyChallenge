package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.service.GeneroService;
import com.alkemy.disney.disney.service.impl.GeneroServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("generos")

public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){
        GeneroDTO generoGuardado = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }

    @GetMapping(value = "/getGenero/{id}")
    public ResponseEntity<GeneroDTO> getById(@PathVariable Long id){
        GeneroDTO genero = generoService.getById(id);
        return ResponseEntity.ok().body(genero);
    }


    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll() {
        List<GeneroDTO> generos = generoService.getAllGeneros();
        return ResponseEntity.ok().body(generos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.generoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

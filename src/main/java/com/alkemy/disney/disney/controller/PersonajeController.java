package com.alkemy.disney.disney.controller;


import com.alkemy.disney.disney.dto.personaje.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.personaje.PersonajeDTO;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("characters")

public class PersonajeController {

    @Autowired
    private PersonajeService personajeService;

    @PostMapping
    public ResponseEntity<PersonajeDTO> save(@RequestBody PersonajeDTO personaje){
        PersonajeDTO personajeGuardado = personajeService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeGuardado);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonajeDTO> update(@PathVariable Long id, @RequestBody PersonajeDTO personaje){
        PersonajeDTO result = personajeService.update(id,personaje);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/getCharacters/{id}")
    public ResponseEntity<PersonajeDTO> getById(@PathVariable Long id){
        PersonajeDTO personaje = personajeService.getById(id);
        return ResponseEntity.ok().body(personaje);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PersonajeBasicDTO>> getDetalisByfilters(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Integer edad,
            @RequestParam(required = false) Integer peso,
            @RequestParam(required = false) List <Long> peliculas,
            @RequestParam(required = false, defaultValue ="ASC") String orden
    ){
        List<PersonajeBasicDTO> personajes = this.personajeService.getbyFilters(nombre, edad, peso, peliculas, orden);
        return ResponseEntity.ok(personajes);
    }

     @GetMapping
   public ResponseEntity<List<PersonajeDTO>> getAll() {
        List<PersonajeDTO> personajes = personajeService.getAllPersonajes();
        return ResponseEntity.ok().body(personajes);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

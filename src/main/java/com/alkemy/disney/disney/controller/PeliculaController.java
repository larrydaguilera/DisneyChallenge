package com.alkemy.disney.disney.controller;

import com.alkemy.disney.disney.dto.pelicula.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.pelicula.PeliculaDTO;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Movies")

public class PeliculaController {

    @Autowired
    private PeliculaService peliculaService;

    @PostMapping
    public ResponseEntity<PeliculaDTO> save(@RequestBody PeliculaDTO pelicula){
        PeliculaDTO peliculaGuardada = peliculaService.save(pelicula);
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaGuardada);
    }
    @GetMapping("/getMovies/{id}")
    public ResponseEntity<PeliculaDTO> getById(@PathVariable Long id){
        PeliculaDTO pelicula = peliculaService.getById(id);
        return ResponseEntity.ok().body(pelicula);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PeliculaDTO> update(@PathVariable Long id, @RequestBody PeliculaDTO pelicula){
        PeliculaDTO result = peliculaService.update(id,pelicula);
        return ResponseEntity.ok().body(result);
    }


    @GetMapping
    public ResponseEntity<List<PeliculaDTO>> getAll() {
        List<PeliculaDTO> peliculas = peliculaService.getAllPeliculas();
        return ResponseEntity.ok().body(peliculas);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<PeliculaBasicDTO>> getDetalisByfilters(
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) Long generoId,
            @RequestParam(required = false, defaultValue ="ASC") String orden
    ) {
        List<PeliculaBasicDTO> peliculas = this.peliculaService.getByFilters(titulo, generoId, orden);
        return ResponseEntity.ok(peliculas);
    }

    @PostMapping("/{id}/addCharacter/{charactersId}")
    public ResponseEntity<PeliculaDTO> addCharactersToMovie(@PathVariable Long peliculaId, @PathVariable Long personajeId) {
        PeliculaDTO result = peliculaService.agregarPersonaje(peliculaId, personajeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/{id}/deleteCharacter/{characterId}")
    public ResponseEntity<PeliculaDTO> eliminarPersonaje(@PathVariable Long peliculaId, @PathVariable Long personajeId) {
        PeliculaDTO result = peliculaService.eliminarPersonaje(peliculaId, personajeId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}

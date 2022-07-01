package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaPersonaje;

import java.util.List;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO dto);
    List<PeliculaDTO> getAllPeliculas();

    PeliculaDTO getById(Long id);

    PeliculaDTO update(Long id, PeliculaDTO pelicula);
}

package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.pelicula.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.pelicula.PeliculaDTO;

import java.util.List;

public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO dto);
    List<PeliculaDTO> getAllPeliculas();

    PeliculaDTO getById(Long id);

    PeliculaDTO update(Long id, PeliculaDTO pelicula);

    List<PeliculaBasicDTO> getByFilters(String tiulo, Long generoId, String orden);

    PeliculaDTO agregarPersonaje(Long peliculaId, Long personajeId);

    void delete(Long id);

    PeliculaDTO eliminarPersonaje(Long peliculaId, Long personajeId);
}

package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;

import java.util.List;

public interface GeneroService {

    GeneroDTO save(GeneroDTO dto);
    List<GeneroDTO> getAllGeneros();

    GeneroDTO getById(Long id);
}

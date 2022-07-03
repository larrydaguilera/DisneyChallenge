package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.genero.GeneroDTO;

import java.util.List;

public interface GeneroService {

    GeneroDTO save(GeneroDTO dto);
    List<GeneroDTO> getAllGeneros();

    GeneroDTO getById(Long id);

    void delete(Long id);

    GeneroDTO update(Long id, GeneroDTO genero);
}

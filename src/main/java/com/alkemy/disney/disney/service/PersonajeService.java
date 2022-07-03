package com.alkemy.disney.disney.service;

import com.alkemy.disney.disney.dto.personaje.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.personaje.PersonajeDTO;

import java.util.List;

public interface PersonajeService {

    PersonajeDTO save(PersonajeDTO dto);
    List<PersonajeDTO> getAllPersonajes();

    PersonajeDTO getById(Long id);

    PersonajeDTO update(Long id, PersonajeDTO personaje);

    List<PersonajeBasicDTO> getbyFilters(String nombre, Integer edad, Integer peso, List<Long> peliculas, String orden);

    void delete(Long id);
}

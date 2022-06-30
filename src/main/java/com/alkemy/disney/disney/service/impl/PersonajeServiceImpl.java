package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaPersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PeliculaPersonaje;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.PeliculaPersonajeMapper;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PeliculaPersonajeRepository;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonajeServiceImpl implements PersonajeService {

    @Autowired
    private PersonajeMapper personajeMapper;
    @Autowired
    private PersonajeRepository personajeRepository;
    @Autowired
    private PeliculaPersonajeRepository peliculaPersonajeRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private PeliculaPersonajeMapper peliculaPersonajeMapper;

    @Transactional
    public PersonajeDTO save(PersonajeDTO dto){
        PersonajeEntity entity = personajeMapper.personajeDTO2Entity(dto);
        PersonajeEntity entitySaved = personajeRepository.save(entity);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(entitySaved);

        dto.getPeliculas().forEach(x ->{
            PeliculaPersonaje peliculaPersonaje = new PeliculaPersonaje();
            peliculaPersonaje.setPersonajeId(result.getId());
            peliculaPersonaje.setPeliculaId(x.getId());
            peliculaPersonajeRepository.save(peliculaPersonaje);
        });

        return result;
    }

    public PersonajeDTO getById(Long id){
        Optional<PersonajeEntity> personaje = personajeRepository.findById(id);
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(personaje.get());
        List<Long> ids = peliculaPersonajeRepository.findAllByPersonajeId(id);
        List<PeliculaPersonajeDTO> dtos = peliculaPersonajeMapper.idList2DTOList(ids);
        List<PeliculaEntity> entities = peliculaPersonajeMapper.peliculaPersonajeDTOList2PeliculaEntityList(dtos);
        result.setPeliculas(entities);

        return result;
    }


    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> entites = personajeRepository.findAll();
        List<PersonajeDTO> result = personajeMapper.personajeEntityList2DTOList(entites);
        return result;
    }
}

package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.PeliculaPersonajeDTO;
import com.alkemy.disney.disney.dto.personaje.PersonajeBasicDTO;
import com.alkemy.disney.disney.dto.personaje.PersonajeDTO;
import com.alkemy.disney.disney.dto.personaje.PersonajeFilterDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PeliculaPersonaje;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.PeliculaPersonajeMapper;
import com.alkemy.disney.disney.mapper.PersonajeMapper;
import com.alkemy.disney.disney.repository.PeliculaPersonajeRepository;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.specification.PersonajeSpecification;
import com.alkemy.disney.disney.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private PersonajeSpecification personajeSpecification;


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


    public PersonajeDTO update(Long id, PersonajeDTO personaje) {
        Optional<PersonajeEntity> entity = personajeRepository.findById(id);
        PersonajeEntity personajeActualizado = personajeMapper.personajeDTO2Entity(personaje);
        if(personajeActualizado.getEdad() != null){entity.get().setEdad(personajeActualizado.getEdad());}
        if(personajeActualizado.getNombre() != null){entity.get().setNombre(personajeActualizado.getNombre());}
        if(personajeActualizado.getImagen() != null){entity.get().setImagen(personajeActualizado.getImagen());}
        if(personajeActualizado.getPeso() != null){entity.get().setPeso(personajeActualizado.getPeso());}
        if(personajeActualizado.getHistoria() != null){entity.get().setHistoria(personajeActualizado.getHistoria());}
        personajeRepository.save(entity.get());
        PersonajeDTO result = personajeMapper.personajeEntity2DTO(personajeRepository.getReferenceById(id));
        List<Long> ids = peliculaPersonajeRepository.findAllByPersonajeId(id);
        List<PeliculaPersonajeDTO> dtos = peliculaPersonajeMapper.idList2DTOList(ids);
        List<PeliculaEntity> entities = peliculaPersonajeMapper.peliculaPersonajeDTOList2PeliculaEntityList(dtos);
        result.setPeliculas(entities);
        return result;
    }


    public List<PersonajeBasicDTO> getbyFilters(String nombre, Integer edad, Integer peso, List<Long> peliculas, String orden) {
        PersonajeFilterDTO filterDTO = new PersonajeFilterDTO(nombre, edad, peso, peliculas, orden);
        List<PersonajeEntity> entites = personajeRepository.findAll(this.personajeSpecification.getByFilters(filterDTO));
        List<PersonajeBasicDTO> dtos = personajeMapper.personajeEntityList2BasicDTO(entites);
        return dtos;
    }


    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeEntity> entites = personajeRepository.findAll();
        List<PersonajeDTO> results = personajeMapper.personajeEntityList2DTOList(entites);
        for( PersonajeDTO result: results){

            List<Long> ids = peliculaPersonajeRepository.findAllByPersonajeId(result.getId());
            List<PeliculaPersonajeDTO> dtos = peliculaPersonajeMapper.idList2DTOList(ids);
            List<PeliculaEntity> entities2 = peliculaPersonajeMapper.peliculaPersonajeDTOList2PeliculaEntityList(dtos);
            result.setPeliculas(entities2);
        }

        return results;
    }

    public void delete(Long id) {
        this.personajeRepository.deleteById(id);
    }
}

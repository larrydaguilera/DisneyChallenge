package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.genero.GeneroBasicDTO;
import com.alkemy.disney.disney.dto.pelicula.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.pelicula.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaPersonajeDTO;
import com.alkemy.disney.disney.dto.pelicula.PeliculaFilterDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.mapper.PeliculaPersonajeMapper;
import com.alkemy.disney.disney.repository.GeneroRepository;
import com.alkemy.disney.disney.repository.PeliculaPersonajeRepository;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import com.alkemy.disney.disney.repository.specification.PeliculaSpecification;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaMapper peliculaMapper;
    @Autowired
    private PeliculaPersonajeMapper peliculaPersonajeMapper;
    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private PeliculaPersonajeRepository peliculaPersonajeRepository;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private GeneroMapper generoMapper;
    @Autowired
    private PeliculaSpecification peliculaSpecification;
    @Autowired
    PersonajeRepository personajeRepository;

    public PeliculaDTO save(PeliculaDTO dto){
        PeliculaEntity entity = peliculaMapper.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(entitySaved);
        result.setPersonajes(checkPersonajesByPeliculaId(result.getId()));
        result.setGenero(checkGeneroByPeliculaId(result.getGeneroId()));
        return result;
    }

    public PeliculaDTO getById(Long id) {
        Optional<PeliculaEntity> pelicula = peliculaRepository.findById(id);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(pelicula.get());
        result.setPersonajes(checkPersonajesByPeliculaId(id));
        result.setGenero(checkGeneroByPeliculaId(id));
        return result;
    }


    public List<PeliculaDTO> getAllPeliculas() {
        List<PeliculaEntity> entities = peliculaRepository.findAll();
        List<PeliculaDTO> results = peliculaMapper.peliculaEntityList2DTOList(entities);
        for( PeliculaDTO result: results){
            result.setGenero(checkGeneroByPeliculaId(result.getId()));
            result.setPersonajes(checkPersonajesByPeliculaId(result.getId()));
        }
        return results;

    }

    public PeliculaDTO update(Long id, PeliculaDTO pelicula) {
        Optional<PeliculaEntity> entity = peliculaRepository.findById(id);
        PeliculaEntity peliculaActualizada = peliculaMapper.peliculaDTO2Entity(pelicula);
        if(peliculaActualizada.getImagen() != null){entity.get().setImagen(peliculaActualizada.getImagen());}
        if(peliculaActualizada.getTitulo() != null){entity.get().setTitulo(peliculaActualizada.getTitulo());}
        if(peliculaActualizada.getFechaCreacion() != null){entity.get().setFechaCreacion(peliculaActualizada.getFechaCreacion());}
        if(peliculaActualizada.getCalificacion() != null){entity.get().setCalificacion(peliculaActualizada.getCalificacion());}
        if(peliculaActualizada.getGeneroId() != null){entity.get().setGeneroId(peliculaActualizada.getGeneroId());}
        peliculaRepository.save(entity.get());
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(peliculaRepository.getReferenceById(id));
        result.setPersonajes(checkPersonajesByPeliculaId(result.getId()));
        result.setGenero(checkGeneroByPeliculaId(result.getGeneroId()));
        return result;
    }

    public List<PeliculaBasicDTO> getByFilters(String titulo,Long generoId, String orden) {
        PeliculaFilterDTO filterDTO = new PeliculaFilterDTO(titulo, generoId, orden);
        List<PeliculaEntity> entites = peliculaRepository.findAll(this.peliculaSpecification.getByFilters(filterDTO));
        List<PeliculaBasicDTO> dtos = peliculaMapper.peliculaEntityList2BasicDTO(entites);
        return dtos;
    }


    public PeliculaDTO agregarPersonaje(Long peliculaId, Long personajeId) {
        PeliculaEntity pelicula = peliculaRepository.findById(peliculaId).get();
        PersonajeEntity personaje = personajeRepository.findById(personajeId).get();
        if(pelicula.getId() != null && personaje.getId() != null){
            peliculaPersonajeRepository.agregarPersonaje(peliculaId,personajeId);
        }
        PeliculaDTO result  = getById(peliculaId);


        return result;
    }
    public PeliculaDTO eliminarPersonaje(Long peliculaId, Long personajeId) {
        peliculaPersonajeRepository.eliminarPersonaje(peliculaId,personajeId);
        PeliculaDTO result  = getById(peliculaId);;
        return result;
    }

    private List<PersonajeEntity> checkPersonajesByPeliculaId(Long id){
        List<Long> ids = peliculaPersonajeRepository.findAllByPeliculaId(id);
        List<PeliculaPersonajeDTO> dtos = peliculaPersonajeMapper.idList2DTOList(ids);
        List<PersonajeEntity> entities = peliculaPersonajeMapper.peliculaPersonajeDTOList2PersonajeEntityList(dtos);
        return entities;
    }

    private GeneroBasicDTO checkGeneroByPeliculaId(Long id){
        GeneroEntity genero = generoRepository.findById(id).get();
        GeneroBasicDTO dto = generoMapper.generoEntity2BasicDTO(genero);
        return dto;
    }

    public void delete(Long id) {
        this.peliculaRepository.deleteById(id);
    }



}

package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaPersonajeDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PeliculaPersonaje;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.PeliculaMapper;
import com.alkemy.disney.disney.mapper.PeliculaPersonajeMapper;
import com.alkemy.disney.disney.repository.GeneroRepository;
import com.alkemy.disney.disney.repository.PeliculaPersonajeRepository;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.service.GeneroService;
import com.alkemy.disney.disney.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public PeliculaDTO save(PeliculaDTO dto){
        PeliculaEntity entity = peliculaMapper.peliculaDTO2Entity(dto);
        PeliculaEntity entitySaved = peliculaRepository.save(entity);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(entitySaved);
        return result;
    }

    public PeliculaDTO getById(Long id) {
        Optional<PeliculaEntity> pelicula = peliculaRepository.findById(id);
        PeliculaDTO result = peliculaMapper.peliculaEntity2DTO(pelicula.get());
        List<Long> ids = peliculaPersonajeRepository.findAllByPeliculaId(id);
        List<PeliculaPersonajeDTO> dtos = peliculaPersonajeMapper.idList2DTOList(ids);
        List<PersonajeEntity> entities = peliculaPersonajeMapper.peliculaPersonajeDTOList2PersonajeEntityList(dtos);
        result.setPersonajes(entities);
        GeneroEntity genero = generoRepository.findById(result.getGeneroId()).get();
        result.setGenero(genero);
        return result;
    }


    public List<PeliculaDTO> getAllPeliculas() {
        List<PeliculaEntity> entities = peliculaRepository.findAll();
        List<PeliculaDTO> results = peliculaMapper.peliculaEntityList2DTOList(entities);
        for( PeliculaDTO result: results){

            List<Long> ids = peliculaPersonajeRepository.findAllByPeliculaId(result.getId());
            List<PeliculaPersonajeDTO> dtos = peliculaPersonajeMapper.idList2DTOList(ids);
            List<PersonajeEntity> entities2 = peliculaPersonajeMapper.peliculaPersonajeDTOList2PersonajeEntityList(dtos);
            result.setPersonajes(entities2);
        }
        return results;
    }


}

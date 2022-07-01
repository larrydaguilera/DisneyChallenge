package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PeliculaPersonajeDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.repository.GeneroRepository;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroMapper generoMapper;
    @Autowired
    private GeneroRepository generoRepository;
    @Autowired
    private PeliculaRepository peliculaRepository;

    public GeneroDTO save(GeneroDTO dto){
        GeneroEntity entity = generoMapper.generoDTO2Entity(dto);
        GeneroEntity entitySaved = generoRepository.save(entity);
        GeneroDTO result = generoMapper.generoEntity2DTO(entitySaved);
        return result;
    }

    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> entites = generoRepository.findAll();
        List<GeneroDTO> results = generoMapper.generoEntityList2DTOList(entites);
        for(GeneroDTO result: results){
            result.setPeliculasList(peliculaRepository.findAllByGeneroId(result.getId()));
        }
        return results;
    }

    public GeneroDTO getById(Long id) {
        Optional<GeneroEntity> genero = generoRepository.findById(id);
        GeneroDTO result = generoMapper.generoEntity2DTO(genero.get());
        result.setPeliculasList(peliculaRepository.findAllByGeneroId(id));
        return result;
    }


    public void delete(Long id) {
        this.generoRepository.deleteById(id);
    }
}

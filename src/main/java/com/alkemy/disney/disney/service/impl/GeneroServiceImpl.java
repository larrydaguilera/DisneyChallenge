package com.alkemy.disney.disney.service.impl;

import com.alkemy.disney.disney.dto.genero.GeneroDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.mapper.GeneroMapper;
import com.alkemy.disney.disney.repository.GeneroRepository;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        result.setPeliculasList(peliculaRepository.findAllByGeneroId(result.getId()));
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


    public GeneroDTO update(Long id, GeneroDTO genero) {
        GeneroEntity entity = generoRepository.findById(id).get();
        GeneroEntity generoActualizado = generoMapper.generoDTO2Entity(genero);
        if(generoActualizado.getImagen() != null){entity.setImagen(generoActualizado.getImagen());}
        if(generoActualizado.getNombre() != null){entity.setNombre(generoActualizado.getNombre());}

        generoRepository.save(entity);
        GeneroDTO result = generoMapper.generoEntity2DTO(generoRepository.getReferenceById(id));
        result.setPeliculasList(peliculaRepository.findAllByGeneroId(id));
        return result;
    }
}

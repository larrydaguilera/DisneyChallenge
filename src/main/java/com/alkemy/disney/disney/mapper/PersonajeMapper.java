package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.GeneroDTO;
import com.alkemy.disney.disney.dto.PeliculaDTO;
import com.alkemy.disney.disney.dto.PersonajeDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;
    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){
        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setImagen(dto.getImagen());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
        /*List<PeliculaEntity> peliculas1 = new ArrayList<>();
        peliculas1 = dto.getPeliculas();
        personajeEntity.setPeliculas(peliculas1)*/;
        for(PeliculaEntity pelicula: dto.getPeliculas()){
            personajeEntity.getPeliculas().add(pelicula);
        }
        return personajeEntity;
    }

    public PersonajeDTO personajeEntity2DTO(PersonajeEntity entity) {
        PersonajeDTO dto = new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setImagen(entity.getImagen());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        /*List<PeliculaEntity> peliculas1 = new ArrayList<>();
        peliculas1 = entity.getPeliculas();
        dto.setPeliculas(peliculas1);*/
        for(PeliculaEntity pelicula: entity.getPeliculas()){
            dto.getPeliculas().add(pelicula);
        }
        return dto;
    }

    public List<PersonajeDTO> personajeEntityList2DTOList(List<PersonajeEntity> entities) {
        List<PersonajeDTO> dtos = new ArrayList<>();
        for (PersonajeEntity entity : entities) {
            dtos.add(this.personajeEntity2DTO(entity));
        }
        return dtos;
    }
}

package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.pelicula.PeliculaBasicDTO;
import com.alkemy.disney.disney.dto.pelicula.PeliculaDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaMapper {

    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto){
        PeliculaEntity peliculaEntity = new PeliculaEntity();
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setFechaCreacion(dto.getFechaCreacion());
        peliculaEntity.setCalificacion(dto.getCalificacion());
        peliculaEntity.setGeneroId(dto.getGeneroId());
        return peliculaEntity;
    }

    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity) {
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setImagen(entity.getImagen());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setCalificacion(entity.getCalificacion());
        dto.setGeneroId(entity.getGeneroId());
        return dto;
    }

    public List<PeliculaDTO> peliculaEntityList2DTOList(List<PeliculaEntity> entites) {
        List<PeliculaDTO> dtos = new ArrayList<>();
        for (PeliculaEntity entity : entites) {
            dtos.add(this.peliculaEntity2DTO(entity));
        }
        return dtos;
    }

    public PeliculaBasicDTO peliculaEntity2BasicDTO(PeliculaEntity entity){
        PeliculaBasicDTO dto = new PeliculaBasicDTO();
        dto.setTitulo(entity.getTitulo());
        dto.setFechaCreacion(entity.getFechaCreacion());
        dto.setImagen(entity.getImagen());
        return dto;
    }

    public List<PeliculaBasicDTO> peliculaEntityList2BasicDTO(List<PeliculaEntity> entites) {
        List<PeliculaBasicDTO> dtos = new ArrayList<>();
        for (PeliculaEntity entity : entites) {
            dtos.add(this.peliculaEntity2BasicDTO(entity));
        }
        return dtos;
    }
}

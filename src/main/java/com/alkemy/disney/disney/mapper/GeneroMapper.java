package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.genero.GeneroBasicDTO;
import com.alkemy.disney.disney.dto.genero.GeneroDTO;
import com.alkemy.disney.disney.entity.GeneroEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMapper {

    public GeneroEntity generoDTO2Entity(GeneroDTO dto){
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setNombre(dto.getNombre());
        generoEntity.setImagen(dto.getImagen());
        return generoEntity;
    }

    public GeneroDTO generoEntity2DTO(GeneroEntity entity) {
        GeneroDTO dto = new GeneroDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setImagen(entity.getImagen());
        return dto;
    }

    public List<GeneroDTO> generoEntityList2DTOList(List<GeneroEntity> entites) {
        List<GeneroDTO> dtos = new ArrayList<>();
        for (GeneroEntity entity : entites) {
            dtos.add(this.generoEntity2DTO(entity));
        }
        return dtos;
    }

    public GeneroBasicDTO generoEntity2BasicDTO(GeneroEntity entity) {
        GeneroBasicDTO dto = new GeneroBasicDTO();
        dto.setNombre(entity.getNombre());
        dto.setImagen(entity.getImagen());
        return dto;
    }
}

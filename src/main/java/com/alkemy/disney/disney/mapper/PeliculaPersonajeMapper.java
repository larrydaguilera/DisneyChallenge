package com.alkemy.disney.disney.mapper;


import com.alkemy.disney.disney.dto.PeliculaPersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.alkemy.disney.disney.repository.PeliculaRepository;
import com.alkemy.disney.disney.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class PeliculaPersonajeMapper {

    @Autowired
    private PeliculaRepository peliculaRepository;
    @Autowired
    private PersonajeRepository personajeRepository;

    public PeliculaPersonajeDTO id2PeliculaPersonajeDTO(Long id){
        PeliculaPersonajeDTO dto = new PeliculaPersonajeDTO();
        dto.setId(id);
        return dto;
    }

    public List<PeliculaPersonajeDTO> idList2DTOList(List<Long> ids) {
        List<PeliculaPersonajeDTO> dtos = new ArrayList<>();
        for (Long id : ids) {
            dtos.add(this.id2PeliculaPersonajeDTO(id));
        }
        return dtos;
    }

    public PeliculaEntity peliculaPersonajeDTO2PeliculaEtity(PeliculaPersonajeDTO dto){
        Optional<PeliculaEntity> entity = Optional.of(new PeliculaEntity());
        entity = peliculaRepository.findById(dto.getId());
        return entity.get();
    }

    public List<PeliculaEntity> peliculaPersonajeDTOList2PeliculaEntityList(List<PeliculaPersonajeDTO> dtos) {
        List<PeliculaEntity> entities = new ArrayList<>();
        for (PeliculaPersonajeDTO dto : dtos) {
            entities.add(this.peliculaPersonajeDTO2PeliculaEtity(dto));
        }
        return entities;
    }

    public PersonajeEntity peliculaPersonajeDTO2PersonajeEtity(PeliculaPersonajeDTO dto){
        Optional<PersonajeEntity> entity = Optional.of(new PersonajeEntity());
        entity = personajeRepository.findById(dto.getId());
        return entity.get();
    }

    public List<PersonajeEntity> peliculaPersonajeDTOList2PersonajeEntityList(List<PeliculaPersonajeDTO> dtos) {
        List<PersonajeEntity> entities = new ArrayList<>();
        for (PeliculaPersonajeDTO dto : dtos) {
            entities.add(this.peliculaPersonajeDTO2PersonajeEtity(dto));
        }
        return entities;
    }
}

package com.alkemy.disney.disney.dto;

import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;
    private String imagen;
    private String titulo;
    private Date fechaCreacion;
    private Integer calificacion;
    private List<PersonajeEntity> personajes;
    private GeneroEntity genero;
    private Long generoId;
}
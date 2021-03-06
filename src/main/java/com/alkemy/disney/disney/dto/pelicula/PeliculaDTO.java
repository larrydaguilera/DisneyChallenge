package com.alkemy.disney.disney.dto.pelicula;

import com.alkemy.disney.disney.dto.genero.GeneroBasicDTO;
import com.alkemy.disney.disney.dto.genero.GeneroDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PeliculaDTO {

    private Long id;
    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
    private Integer calificacion;
    private List<PersonajeEntity> personajes;
    private GeneroBasicDTO genero;
    private Long generoId;
}

package com.alkemy.disney.disney.dto.pelicula;

import com.alkemy.disney.disney.dto.genero.GeneroDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class PeliculaBasicDTO {


    private String imagen;
    private String titulo;
    private LocalDate fechaCreacion;
}

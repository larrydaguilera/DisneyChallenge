package com.alkemy.disney.disney.dto.personaje;

import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PeliculaPersonaje;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PersonajeDTO {

    private Long id;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Integer peso;

    private String historia;

    private List<PeliculaEntity> peliculas;

}

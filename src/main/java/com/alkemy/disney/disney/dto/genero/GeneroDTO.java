package com.alkemy.disney.disney.dto.genero;

import com.alkemy.disney.disney.entity.PeliculaEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GeneroDTO {

    private Long id;
    private String nombre;
    private String imagen;
    private List <PeliculaEntity> peliculasList;
}

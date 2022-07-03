package com.alkemy.disney.disney.dto.pelicula;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class PeliculaFilterDTO {


    private String titulo;

    private Long generoId;

    private String orden;


    public PeliculaFilterDTO(String titulo, Long generoId, String orden) {
        this.titulo = titulo;
        this.generoId = generoId;
        this.orden = orden;
    }

    public boolean isASC() { return orden.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC() { return orden.compareToIgnoreCase("DESC") == 0;}

}

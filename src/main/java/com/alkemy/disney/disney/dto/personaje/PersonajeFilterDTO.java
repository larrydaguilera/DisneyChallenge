package com.alkemy.disney.disney.dto.personaje;

import com.alkemy.disney.disney.entity.PeliculaEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Setter
@Getter
public class PersonajeFilterDTO {


    private String nombre;

    private Integer edad;

    private Integer peso;

    private List<Long> peliculas;

    private String orden;


    public PersonajeFilterDTO(String nombre, Integer edad, Integer peso, List<Long> peliculas, String orden) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
        this.peliculas = peliculas;
        this.orden = orden;
    }

    public boolean isASC() { return orden.compareToIgnoreCase("ASC") == 0;}
    public boolean isDESC() { return orden.compareToIgnoreCase("DESC") == 0;}

}

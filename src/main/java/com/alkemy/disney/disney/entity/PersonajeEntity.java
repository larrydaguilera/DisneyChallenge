package com.alkemy.disney.disney.entity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "personaje")
@Getter
@Setter

public class PersonajeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private Long edad;//años

    private Long peso;// Kg

    private String historia;

    /*@ManyToMany( cascade = CascadeType.ALL
            /* cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )*/
    /*@JoinTable(
            name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "personaje_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pelicula_id", referencedColumnName = "id")
    )*/
    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PeliculaEntity> peliculas = new ArrayList<>();


}

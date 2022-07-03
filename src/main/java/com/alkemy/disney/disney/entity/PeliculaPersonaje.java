package com.alkemy.disney.disney.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "pelicula_personaje")
public class PeliculaPersonaje implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "personaje_id")
    private Long personajeId;

    @Column(name = "pelicula_id")
    private Long peliculaId;

    private boolean deleted = Boolean.FALSE;

}
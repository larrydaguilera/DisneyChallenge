package com.alkemy.disney.disney.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "genero")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genero SET deleted = true Where id=?")
@Where(clause = "deleted=false")

public class GeneroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String imagen;

    private boolean deleted = Boolean.FALSE;

    @JsonIgnore
    @OneToMany(mappedBy = "genero")
    List<PeliculaEntity> peliculasList;



}

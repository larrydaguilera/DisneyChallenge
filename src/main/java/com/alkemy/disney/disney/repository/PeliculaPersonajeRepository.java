package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.dto.PeliculaPersonajeDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PeliculaPersonaje;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PeliculaPersonajeRepository extends JpaRepository<PeliculaPersonaje, Long> {

    @Query(value = "select pp.personaje_id " +
            "from pelicula_personaje pp, " +
            "personaje p , " +
            "pelicula p2  " +
            "where pp.pelicula_id = :id " +
            "and  p.id = pp.personaje_id " +
            "and p2.id = pp.pelicula_id ",nativeQuery = true)
    List<Long> findAllByPeliculaId(Long id);

    @Query(value = "select pp.pelicula_id " +
            "from pelicula_personaje pp, " +
            "personaje p , " +
            "pelicula p2  " +
            "where pp.personaje_id = :id " +
            "and  p.id = pp.personaje_id " +
            "and p2.id = pp.pelicula_id" ,nativeQuery = true)
    List<Long> findAllByPersonajeId(Long id);

}

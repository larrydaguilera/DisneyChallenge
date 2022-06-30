package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PeliculaPersonaje;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {

    /*@Query(value = "select p.id, p.calificacion, p.fecha_creacion, p.genero_id, p.imagen, p.titulo" +
            " from pelicula p inner join pelicula_personaje pp. where pp.personaje_id = id" ,
            nativeQuery = true)
    @Query(value = "select pp.personaje_id \n" +
            "from pelicula_personaje pp,\n" +
            "personaje p ,\n" +
            "pelicula p2 \n" +
            "where pp.personaje_id = 28\n" +
            "and  p.id = pp.personaje_id\n" +
            "and p2.id = pp.pelicula_id ", nativeQuery = true)
    List<PeliculaEntity> findAllByPersonajeId(Long id);*/
}

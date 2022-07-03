package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.PeliculaPersonaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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

    @Modifying
    @Query(value="insert into pelicula_personaje(pelicula_id, personaje_id)\n" +
            "values(:peliculaId, :personajeId)", nativeQuery = true)
    public Void agregarPersonaje(Long peliculaId, Long personajeId);

    @Query(value="update genero set deleted = 1  where pelicula_id= :peliculaId and personaje_id = :personajeId", nativeQuery= true)
    public Void eliminarPersonaje(Long peliculaId, Long personajeId);

}

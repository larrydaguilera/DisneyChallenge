package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.entity.GeneroEntity;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PeliculaPersonaje;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long> {

@Query(value = "Select * From pelicula where genero_id = :id", nativeQuery = true)
List<PeliculaEntity> findAllByGeneroId(Long id);

List<PeliculaEntity> findAll(Specification<PeliculaEntity> spec);
}

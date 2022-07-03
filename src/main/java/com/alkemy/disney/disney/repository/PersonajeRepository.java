package com.alkemy.disney.disney.repository;

import com.alkemy.disney.disney.dto.personaje.PersonajeDTO;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonajeRepository extends JpaRepository<PersonajeEntity, Long> {

    List<PersonajeEntity> findAll(Specification<PersonajeEntity> spec);

}

package com.alkemy.disney.disney.repository.specification;

import com.alkemy.disney.disney.dto.pelicula.PeliculaFilterDTO;
import com.alkemy.disney.disney.dto.personaje.PersonajeFilterDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaSpecification {

    public Specification<PeliculaEntity> getByFilters(PeliculaFilterDTO filterDto){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDto.getTitulo())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filterDto.getTitulo().toLowerCase() + "%"
                        )
                );
            }
            if ((filterDto.getGeneroId() != null)){
                predicates.add(criteriaBuilder.equal(root.get("genero_id"), filterDto.getGeneroId()));
            }
            //Reomve duplicates
            query.distinct(true);

            //Order resolver
            if(filterDto.getOrden() != null){
                String ordenarPorCampo = "titulo";
                query.orderBy(
                        filterDto.isASC()?
                                criteriaBuilder.asc(root.get(ordenarPorCampo)):
                                criteriaBuilder.desc(root.get(ordenarPorCampo))
                );
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });


    }

}

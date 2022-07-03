package com.alkemy.disney.disney.repository.specification;

import com.alkemy.disney.disney.dto.personaje.PersonajeFilterDTO;
import com.alkemy.disney.disney.entity.PeliculaEntity;
import com.alkemy.disney.disney.entity.PersonajeEntity;
import org.hibernate.mapping.Collection;
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
public class PersonajeSpecification {
    public Specification<PersonajeEntity> getByFilters(PersonajeFilterDTO filterDTO){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filterDTO.getNombre())){
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filterDTO.getNombre().toLowerCase() + "%"));
            }

            if (filterDTO.getPeso() != null){
                predicates.add(criteriaBuilder.equal(root.get("peso"), filterDTO.getPeso()));}
            if (filterDTO.getEdad() != null){
                predicates.add(criteriaBuilder.equal(root.get("edad"), filterDTO.getEdad()));}
            if (!CollectionUtils.isEmpty(filterDTO.getPeliculas())){
                Join<PeliculaEntity,PersonajeEntity> join = root.join("pelicula", JoinType.INNER);
                Expression<String> peliculasId = join.get("id");
                predicates.add(peliculasId.in(filterDTO.getPeliculas()));
            }

            //Remove Duplicates
            query.distinct(true);

            //Order resolver
            if(filterDTO.getOrden() !=null){
                String ordenarPorCampo = "nombre";
                query.orderBy(
                        filterDTO.isASC() ?
                                criteriaBuilder.asc(root.get(ordenarPorCampo)):
                                criteriaBuilder.desc(root.get(ordenarPorCampo))
                );
            }



            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}



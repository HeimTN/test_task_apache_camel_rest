package com.digitalfuture.vacancy.repository.specification;

import com.digitalfuture.vacancy.obj.Vacancy;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class VacancySpecification implements Specification<Vacancy> {
    private String name;
    private String positionName;
    private String city;

    public VacancySpecification(String name, String position, String city){
        this.name = name;
        this.city = city;
        this.positionName = position;
    }

    @Override
    public Predicate toPredicate(Root<Vacancy> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = criteriaBuilder.conjunction();
        if(name != null){
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("name"), "%" + name + "%"));
        }
        if (positionName != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.like(root.get("positionName"), "%" + positionName + "%"));
        }

        if (city != null) {
            predicate = criteriaBuilder.and(predicate, criteriaBuilder.equal(root.get("city"), city));
        }


        return predicate;
    }
}

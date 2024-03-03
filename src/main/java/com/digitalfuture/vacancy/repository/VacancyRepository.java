package com.digitalfuture.vacancy.repository;

import com.digitalfuture.vacancy.obj.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {
    Collection<Vacancy> findVacanciesByName(String name);
    Collection<Vacancy> findVacanciesByCity(String city);
    Collection<Vacancy> findVacanciesByPositionName(String positionName);
}

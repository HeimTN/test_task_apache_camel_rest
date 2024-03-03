package com.digitalfuture.vacancy.services;

import com.digitalfuture.vacancy.dto.VacancyDTO;

import java.util.Collection;

public interface VacancyService {
    boolean addVacancy(VacancyDTO vacancyDTO);

    Collection<VacancyDTO> getFilteredCollectionVacancy(String filter, String key);

    Collection<VacancyDTO> getCollectionVacancy();
}

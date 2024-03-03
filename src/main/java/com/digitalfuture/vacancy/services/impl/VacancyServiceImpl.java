package com.digitalfuture.vacancy.services.impl;

import com.digitalfuture.vacancy.dto.VacancyDTO;
import com.digitalfuture.vacancy.obj.Vacancy;
import com.digitalfuture.vacancy.repository.VacancyRepository;
import com.digitalfuture.vacancy.services.VacancyService;
import com.digitalfuture.vacancy.util.VacancyMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    public VacancyServiceImpl(VacancyRepository vacancyRepository){
        this.vacancyRepository = vacancyRepository;
    }
    @Override
    public boolean addVacancy(VacancyDTO vacancyDTO){
        Vacancy vacancy = VacancyMapper.mappingVacancyDTOtoVacancy(vacancyDTO);
        try{
            vacancyRepository.save(vacancy);
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Collection<VacancyDTO> getFilteredCollectionVacancy(String filter, String key) {
        return switch (filter) {
            case "name" ->
                    VacancyMapper.mappingCollectionVacancyToCollectionVacancyDTO(vacancyRepository.findVacanciesByName(key));
            case "position" ->
                    VacancyMapper.mappingCollectionVacancyToCollectionVacancyDTO(vacancyRepository.findVacanciesByPositionName(key));
            case "city" ->
                    VacancyMapper.mappingCollectionVacancyToCollectionVacancyDTO(vacancyRepository.findVacanciesByCity(key));
            default -> Collections.EMPTY_LIST;
        };
    }

    @Override
    public Collection<VacancyDTO> getCollectionVacancy() {
        return VacancyMapper.mappingCollectionVacancyToCollectionVacancyDTO(vacancyRepository.findAll());
    }
}

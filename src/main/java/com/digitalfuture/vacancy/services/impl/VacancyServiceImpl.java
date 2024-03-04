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
    public Collection<VacancyDTO> getFilteredCollectionVacancy(String name, String position, String city) {
        Collection<Vacancy> vacancies = Collections.EMPTY_LIST;
        if(name != null){
            vacancies = vacancyRepository.findVacanciesByName(name);
        }
        if(position != null){
            if(!vacancies.isEmpty()){
               vacancies = vacancies.stream().filter(vacancy -> vacancy.getPositionName().equals(position)).toList();
            } else vacancies = vacancyRepository.findVacanciesByPositionName(position);
        }
        if(city != null){
            if(!vacancies.isEmpty()){
                vacancies = vacancies.stream().filter(vacancy -> vacancy.getCity().equals(city)).toList();
            } else vacancies = vacancyRepository.findVacanciesByCity(city);
        }
        return VacancyMapper.mappingCollectionVacancyToCollectionVacancyDTO(vacancies);
    }

    @Override
    public Collection<VacancyDTO> getCollectionVacancy() {
        return VacancyMapper.mappingCollectionVacancyToCollectionVacancyDTO(vacancyRepository.findAll());
    }
}

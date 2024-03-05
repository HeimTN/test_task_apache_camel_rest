package com.digitalfuture.vacancy.services.impl;

import com.digitalfuture.vacancy.dto.VacancyDTO;
import com.digitalfuture.vacancy.obj.Vacancy;
import com.digitalfuture.vacancy.repository.VacancyRepository;
import com.digitalfuture.vacancy.repository.specification.VacancySpecification;
import com.digitalfuture.vacancy.services.VacancyService;
import com.digitalfuture.vacancy.util.VacancyMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class VacancyServiceImpl implements VacancyService {

    private final VacancyRepository vacancyRepository;
    private final VacancyMapper vacancyMapper;
    public VacancyServiceImpl(VacancyRepository vacancyRepository, VacancyMapper vacancyMapper){
        this.vacancyRepository = vacancyRepository;
        this.vacancyMapper = vacancyMapper;
    }
    @Override
    public boolean addVacancy(VacancyDTO vacancyDTO){
        Vacancy vacancy = vacancyMapper.mappingVacancyDTOtoVacancy(vacancyDTO);
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
        Collection<Vacancy> vacancies = vacancyRepository.findAll(new VacancySpecification(name, position, city));
        return vacancyMapper.mappingCollectionVacancyToCollectionVacancyDTO(vacancies);
    }

    @Override
    public Collection<VacancyDTO> getCollectionVacancy() {
        return vacancyMapper.mappingCollectionVacancyToCollectionVacancyDTO(vacancyRepository.findAll());
    }
}

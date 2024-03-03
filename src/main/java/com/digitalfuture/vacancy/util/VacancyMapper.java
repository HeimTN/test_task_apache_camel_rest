package com.digitalfuture.vacancy.util;

import com.digitalfuture.vacancy.dto.VacancyDTO;
import com.digitalfuture.vacancy.obj.Vacancy;

import java.util.Collection;
import java.util.stream.Collectors;

public class VacancyMapper {
    public static Vacancy mappingVacancyDTOtoVacancy(VacancyDTO vacancyDTO){
        Vacancy vacancy = new Vacancy();
        vacancy.setName(vacancyDTO.getName());
        vacancy.setDescription(vacancyDTO.getDescription());
        vacancy.setCity(vacancyDTO.getCity());
        vacancy.setPositionName(vacancyDTO.getPositionName());
        vacancy.setSalaryRate(vacancyDTO.getSalaryRate());
        vacancy.setWorkExperience(vacancyDTO.getWorkExperience());
        return vacancy;
    }
    public static VacancyDTO mappingVacancyToVacancyDTO(Vacancy vacancy){
        VacancyDTO vacancyDTO = new VacancyDTO();
        vacancyDTO.setName(vacancy.getName());
        vacancyDTO.setDescription(vacancy.getDescription());
        vacancyDTO.setCity(vacancy.getCity());
        vacancyDTO.setPositionName(vacancy.getPositionName());
        vacancyDTO.setSalaryRate(vacancy.getSalaryRate());
        vacancyDTO.setWorkExperience(vacancy.getWorkExperience());
        return vacancyDTO;
    }

    public static Collection<VacancyDTO> mappingCollectionVacancyToCollectionVacancyDTO(Collection<Vacancy> vacancies){
        return vacancies.stream().map(VacancyMapper::mappingVacancyToVacancyDTO).collect(Collectors.toList());
    }
}

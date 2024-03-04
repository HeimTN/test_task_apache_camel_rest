package com.digitalfuture.vacancy.util;

import com.digitalfuture.vacancy.dto.VacancyDTO;
import com.digitalfuture.vacancy.obj.Vacancy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class VacancyMapperTest {
    
    Vacancy vacancy;
    VacancyDTO vacancyDTO;
    Collection<Vacancy> vacancies;
    VacancyMapper vacancyMapper;
    
    @BeforeEach
    public void start(){
        vacancyMapper = new VacancyMapper();
        vacancy = new Vacancy();
        vacancy.setName("test");
        vacancy.setCity("monako");
        vacancy.setPositionName("Kolbasa");
        vacancy.setDescription("Vkysnaya");
        vacancy.setSalaryRate(900);
        vacancy.setWorkExperience("Hleb");

        vacancyDTO = new VacancyDTO();
        vacancyDTO.setName("testDTO");
        vacancyDTO.setCity("monakoDTO");
        vacancyDTO.setPositionName("KolbasaDTO");
        vacancyDTO.setDescription("VkysnayaDTO");
        vacancyDTO.setSalaryRate(999);
        vacancyDTO.setWorkExperience("HlebDTO");

        Random random = new Random();
        String[] names = {"Test 1", "Test 2", "Test 3"};
        String[] cities = {"Monaco", "Paris", "Berlin"};
        String[] positions = {"Software Engineer", "Data Scientist", "Product Manager"};
        String[] descriptions = {"Exciting opportunity", "Cutting-edge projects", "Innovative company"};
        int[] salaryRates = {1000, 2000, 3000};
        String[] workExperiences = {"3+ years", "5+ years", "7+ years"};
        vacancies = Stream.generate(() ->{
            Vacancy temp = new Vacancy();
            temp.setName(names[random.nextInt(names.length)]);
            temp.setCity(cities[random.nextInt(cities.length)]);
            temp.setPositionName(positions[random.nextInt(positions.length)]);
            temp.setDescription(descriptions[random.nextInt(descriptions.length)]);
            temp.setSalaryRate(salaryRates[random.nextInt(salaryRates.length)]);
            temp.setWorkExperience(workExperiences[random.nextInt(workExperiences.length)]);
            return temp;
        }).limit(20).collect(Collectors.toList());
    }
    @Test
    public void testMappingVacancyDTOtoVacancy(){
        Vacancy vacancyTemp = vacancyMapper.mappingVacancyDTOtoVacancy(vacancyDTO);
        Assertions.assertEquals(vacancyDTO.getName(), vacancyTemp.getName());
        Assertions.assertEquals(vacancyDTO.getCity(), vacancyTemp.getCity());
        Assertions.assertEquals(vacancyDTO.getPositionName(), vacancyTemp.getPositionName());
        Assertions.assertEquals(vacancyDTO.getDescription(), vacancyTemp.getDescription());
        Assertions.assertEquals(vacancyDTO.getSalaryRate(), vacancyTemp.getSalaryRate());
        Assertions.assertEquals(vacancyDTO.getWorkExperience(), vacancyTemp.getWorkExperience());
    }

    @Test
    public void testMappingVacancyToVacancyDTO(){
        VacancyDTO vacancyDTOTemp = vacancyMapper.mappingVacancyToVacancyDTO(vacancy);
        Assertions.assertEquals(vacancy.getName(), vacancyDTOTemp.getName());
        Assertions.assertEquals(vacancy.getCity(), vacancyDTOTemp.getCity());
        Assertions.assertEquals(vacancy.getPositionName(), vacancyDTOTemp.getPositionName());
        Assertions.assertEquals(vacancy.getDescription(), vacancyDTOTemp.getDescription());
        Assertions.assertEquals(vacancy.getSalaryRate(), vacancyDTOTemp.getSalaryRate());
        Assertions.assertEquals(vacancy.getWorkExperience(), vacancyDTOTemp.getWorkExperience());
    }

    @Test
    public void testMappingCollectionVacancyToCollectionVacancyDTO(){
        Random random = new Random();
        Collection<VacancyDTO> vacancyDTOS = vacancyMapper.mappingCollectionVacancyToCollectionVacancyDTO(vacancies);
        Assertions.assertEquals(vacancies.size(), vacancyDTOS.size());

    }
}

package com.digitalfuture.vacancy.services;

import com.digitalfuture.vacancy.dto.VacancyDTO;
import com.digitalfuture.vacancy.obj.Vacancy;
import com.digitalfuture.vacancy.repository.VacancyRepository;
import com.digitalfuture.vacancy.repository.specification.VacancySpecification;
import com.digitalfuture.vacancy.services.impl.VacancyServiceImpl;
import com.digitalfuture.vacancy.util.VacancyMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class VacancyServiceTest {
    @Mock
    VacancyRepository vacancyRepository;
    @Spy
    VacancyMapper vacancyMapper;
    @InjectMocks
    VacancyServiceImpl vacancyService;

    @Test
    public void testAddVacancy_Success() {
       VacancyDTO testVacancyDTO = new VacancyDTO();
        boolean result = vacancyService.addVacancy(testVacancyDTO);
        Assertions.assertTrue(result);
        verify(vacancyRepository, times(1)).save(any(Vacancy.class));
    }
    @Test
    public void testAddVacancy_Fail(){
       VacancyDTO testVacancyDTO = new VacancyDTO();
        when(vacancyRepository.save(any(Vacancy.class))).thenThrow(new RuntimeException());
        boolean result = vacancyService.addVacancy(testVacancyDTO);
        Assertions.assertFalse(result);
    }

    @Test
    public void testGetFilteredCollectionVacancy_OneFilter(){
        Collection<Vacancy> vacancies = Stream.generate(this::generateRandomVacancy).limit(50).collect(Collectors.toList());
        when(vacancyRepository.findAll(any(VacancySpecification.class))).thenReturn(vacancies.stream().filter(vacancy -> vacancy.getName().equals("Google")).collect(Collectors.toList()));
        Collection<VacancyDTO> result = vacancyService.getFilteredCollectionVacancy("Google", null, null);
        Assertions.assertTrue(result.stream().allMatch(vacancyDTO -> vacancyDTO.getName().equals("Google")));
        verify(vacancyRepository, times(1)).findAll(any(VacancySpecification.class));
    }

    @Test
    public void testGetFilteredCollectionVacancy_TwoFilter(){
        Collection<Vacancy> vacancies = Stream.generate(this::generateRandomVacancy).limit(50).collect(Collectors.toList());
        when(vacancyRepository.findAll(any(VacancySpecification.class))).thenReturn(vacancies.stream()
                .filter(vacancy -> vacancy.getName().equals("Google") && vacancy.getPositionName().equals("Data Scientist")).collect(Collectors.toList()));

        Collection<VacancyDTO> result = vacancyService.getFilteredCollectionVacancy("Google", "Data Scientist", null);
        Assertions.assertTrue(result.stream().allMatch(vacancyDTO -> vacancyDTO.getName().equals("Google") && vacancyDTO.getPositionName().equals("Data Scientist")));
    }

    @Test
    public void testGetFilteredCollectionVacancy_TreeFilter(){
        Collection<Vacancy> vacancies = Stream.generate(this::generateRandomVacancy).limit(50).collect(Collectors.toList());
        when(vacancyRepository.findAll(any(VacancySpecification.class))).thenReturn(vacancies.stream()
                .filter(vacancy -> vacancy.getName().equals("Google") && vacancy.getPositionName().equals("Data Scientist") && vacancy.getCity().equals("Redmond")).collect(Collectors.toList()));

        Collection<VacancyDTO> result = vacancyService.getFilteredCollectionVacancy("Google", "Data Scientist", "Redmond");
        Assertions.assertTrue(result.stream().allMatch(vacancyDTO -> vacancyDTO.getName().equals("Google") && vacancyDTO.getPositionName().equals("Data Scientist")
                && vacancyDTO.getCity().equals("Redmond")));
        verify(vacancyRepository, times(1)).findAll(any(VacancySpecification.class));
    }

    @Test
    public void testGetFilteredCollectionVacancy_NullFilter(){
        Collection<VacancyDTO> result = vacancyService.getFilteredCollectionVacancy(null, null, null);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testGetCollectionVacancy(){
        List<Vacancy> vacancies = Stream.generate(this::generateRandomVacancy).limit(50).collect(Collectors.toList());
        when(vacancyRepository.findAll()).thenReturn(vacancies);
        Collection<VacancyDTO> result = vacancyService.getCollectionVacancy();
        Assertions.assertEquals(50, result.size());
    }

    private Vacancy generateRandomVacancy(){
        Random random = new Random();
        String[] positions = {"Software Engineer", "Data Scientist", "Product Manager"};
        String[] name = {"Google", "Microsoft", "Apple"};
        String[] city = {"Mountain View", "Redmond", "Cupertino"};
        int positionInd = random.nextInt(positions.length);
        int nameInd = random.nextInt(name.length);
        int cityInd = random.nextInt(city.length);
        Vacancy result = new Vacancy();
        result.setName(name[nameInd]);
        result.setCity(city[cityInd]);
        result.setPositionName(positions[positionInd]);
        return result;
    }

}

package com.digitalfuture.vacancy.services;

import com.digitalfuture.vacancy.dto.MailingDTO;
import com.digitalfuture.vacancy.obj.Candidate;
import com.digitalfuture.vacancy.obj.Vacancy;
import com.digitalfuture.vacancy.repository.CandidateRepository;
import com.digitalfuture.vacancy.repository.VacancyRepository;
import com.digitalfuture.vacancy.services.impl.SubscribeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SubscribeServiceTest {
    @Mock
    CandidateRepository candidateRepository;
    @Mock
    VacancyRepository vacancyRepository;
    @InjectMocks
    SubscribeServiceImpl subscribeService;

    @Test
    public void testSubscribeCandidate_CandidateExist(){
        String email = "test@example.com";
        Candidate candidate = new Candidate();
        candidate.setEmail(email);

        when(candidateRepository.findCandidateByEmail(email)).thenReturn(Optional.of(candidate));
        candidate.setJobSubscription(true);
        boolean result = subscribeService.subscribeCandidate(email);

        Assertions.assertTrue(result);
        verify(candidateRepository, times(1)).save(candidate);
    }

    @Test
    public void testSubscribeCandidate_CandidateNotExist(){
        String email = "test@example.com";
        when(candidateRepository.findCandidateByEmail(email)).thenReturn(Optional.empty());
        boolean result = subscribeService.subscribeCandidate(email);

        Assertions.assertFalse(result);
        verify(candidateRepository, never()).save(any());
    }

    @Test
    public void testSubCandidates_CandidateExist(){
        Candidate candidate1 = new Candidate();
        Candidate candidate2 = new Candidate();
        Candidate candidate3 = new Candidate();
        Candidate candidate4 = new Candidate();
        candidate1.setJobSubscription(true);
        candidate4.setJobSubscription(true);
        Collection<Candidate> candidates = Arrays.asList(candidate1, candidate2, candidate3, candidate4);

        when(candidateRepository.findCandidateByJobSubscription(true)).thenReturn(candidates.stream().filter(Candidate::isJobSubscription).collect(Collectors.toList()));
        Collection<Candidate> result = subscribeService.subCandidates();
        Assertions.assertEquals(2, result.size());
        Assertions.assertTrue(result.contains(candidate1));
        Assertions.assertFalse(result.contains(candidate2));
    }

    @Test
    public void testSubCandidates_CandidateNotExist(){
        Candidate candidate1 = new Candidate();
        Candidate candidate2 = new Candidate();
        Candidate candidate3 = new Candidate();
        Candidate candidate4 = new Candidate();
        Collection<Candidate> candidates = Arrays.asList(candidate1, candidate2, candidate3, candidate4);

        when(candidateRepository.findCandidateByJobSubscription(true)).thenReturn(candidates.stream().filter(Candidate::isJobSubscription).collect(Collectors.toList()));
        Collection<Candidate> result = subscribeService.subCandidates();
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    public void testCreateMailData(){
        Candidate candidate = new Candidate();
        candidate.setPositionInterest("QA");
        Collection<Vacancy> vacancies = Stream.generate(this::generateRandomVacancy).limit(50).toList();
        when(vacancyRepository.findVacanciesByPositionName(candidate.getPositionInterest()))
                .thenReturn(vacancies.stream().filter(vacancy -> vacancy.getPositionName().equals(candidate.getPositionInterest())).collect(Collectors.toList()));

        Collection<MailingDTO> result = subscribeService.createMailData(candidate);
        Assertions.assertTrue(result.stream().allMatch(mailingDTO -> mailingDTO.getVacancy().getPositionName().equals(candidate.getPositionInterest())));
    }
    @Test
    public void testBuildEmailBody(){
        Candidate candidate = new Candidate();
        candidate.setName("John Doe");

        Vacancy vacancy = new Vacancy();
        vacancy.setPositionName("Software Engineer");
        vacancy.setName("Backend Developer");
        vacancy.setDescription("Exciting opportunity to work on cutting-edge projects");
        vacancy.setSalaryRate(120000);
        vacancy.setWorkExperience("3+ years");

        LocalDate time = LocalDate.of(2024, 3, 5);
        MailingDTO mailingDTO = new MailingDTO();
        mailingDTO.setCandidate(candidate);
        mailingDTO.setVacancy(vacancy);
        mailingDTO.setTime(time);

        String expectedEmailBody = "Здравствуйте, John Doe!\n\n" +
                "Информируем вас о новой вакансии на должность Software Engineer.\n" +
                "Наименование: Backend Developer\n" +
                "Описание: Exciting opportunity to work on cutting-edge projects\n" +
                "Уровень зарплаты: 120000\n" +
                "Требуемый опыт работы: 3+ years\n\n" +
                "С уважением,\nЦифровое Будущее\n05.03.2024";

        String emailBody = subscribeService.buildEmailBody(mailingDTO);

        Assertions.assertEquals(expectedEmailBody, emailBody);
    }


    private Vacancy generateRandomVacancy(){
        Random random = new Random();
        String[] positions = {"Software Engineer", "Data Scientist", "Product Manager", "QA"};
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

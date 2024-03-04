package com.digitalfuture.vacancy.services.impl;

import com.digitalfuture.vacancy.dto.MailingDTO;
import com.digitalfuture.vacancy.obj.Candidate;
import com.digitalfuture.vacancy.obj.Vacancy;
import com.digitalfuture.vacancy.repository.CandidateRepository;
import com.digitalfuture.vacancy.repository.VacancyRepository;
import com.digitalfuture.vacancy.repository.specification.VacancySpecification;
import com.digitalfuture.vacancy.services.SubscribeService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Service
public class SubscribeServiceImpl implements SubscribeService {

    private final CandidateRepository candidateRepository;
    private final VacancyRepository vacancyRepository;
    public SubscribeServiceImpl(CandidateRepository candidateRepository, VacancyRepository vacancyRepository){
        this.candidateRepository = candidateRepository;
        this.vacancyRepository = vacancyRepository;
    }

    @Override
    public boolean subscribeCandidate(String email) {
        Candidate candidate = candidateRepository.findCandidateByEmail(email).orElse(null);
        if(candidate != null){
            candidate.setJobSubscription(true);
            candidateRepository.save(candidate);
            return true;
        }else return false;
    }

    @Override
    public Collection<Candidate> subCandidates(){
        return candidateRepository.findCandidateByJobSubscription(true);
    }

    @Override
    public Collection<MailingDTO> createMailData(Candidate candidate){
        Collection<Vacancy> vacancies = vacancyRepository.findAll(new VacancySpecification(null, candidate.getPositionInterest(), null));
        Collection<MailingDTO> result = vacancies.stream().map(vacancy -> {
            MailingDTO temp = new MailingDTO();
            temp.setVacancy(vacancy);
            temp.setCandidate(candidate);
            temp.setTime(LocalDate.now());
            return temp;
        }).toList();
        return result;
    }

    @Override
    public String buildEmailBody(MailingDTO mailingDTO){
        String emailBody = "Здравствуйте, " + mailingDTO.getCandidate().getName() + "!\n\n" +
                "Информируем вас о новой вакансии на должность " + mailingDTO.getVacancy().getPositionName() + ".\n" +
                "Наименование: " + mailingDTO.getVacancy().getName() + "\n" +
                "Описание: " + mailingDTO.getVacancy().getDescription() + "\n" +
                "Уровень зарплаты: " + mailingDTO.getVacancy().getSalaryRate() + "\n" +
                "Требуемый опыт работы: " + mailingDTO.getVacancy().getWorkExperience() + "\n\n" +
                "С уважением,\nЦифровое Будущее\n" + mailingDTO.getTime().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return emailBody;
    }
}

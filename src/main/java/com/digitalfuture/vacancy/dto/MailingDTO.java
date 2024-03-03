package com.digitalfuture.vacancy.dto;

import com.digitalfuture.vacancy.obj.Candidate;
import com.digitalfuture.vacancy.obj.Vacancy;

import java.time.LocalDate;

public class MailingDTO {
    private Candidate candidate;
    private Vacancy vacancy;
    private LocalDate time;

    public Candidate getCandidate() {
        return candidate;
    }
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
    public Vacancy getVacancy() {
        return vacancy;
    }
    public void setVacancy(Vacancy vacancy) {
        this.vacancy = vacancy;
    }
    public LocalDate getTime() {
        return time;
    }
    public void setTime(LocalDate time) {
        this.time = time;
    }
}

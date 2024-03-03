package com.digitalfuture.vacancy.services;

import com.digitalfuture.vacancy.dto.MailingDTO;
import com.digitalfuture.vacancy.obj.Candidate;

import java.util.Collection;

public interface SubscribeService {
    boolean subscribeCandidate(String name);
    Collection<Candidate> subCandidates();
    Collection<MailingDTO> createMailData(Candidate candidate);
    String buildEmailBody(MailingDTO mailingDTO);
}

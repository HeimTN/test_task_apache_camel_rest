package com.digitalfuture.vacancy.repository;

import com.digitalfuture.vacancy.obj.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface CandidateRepository extends JpaRepository<Candidate,Long> {
    Optional<Candidate> findCandidateByEmail(String email);
    Collection<Candidate> findCandidateByJobSubscription(boolean status);

}

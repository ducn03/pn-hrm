package com.premiernuts.hrm.jpa.repository.candidate;

import com.premiernuts.hrm.jpa.domain.candidate.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long>, JpaSpecificationExecutor<Candidate> {

    List<Candidate> findAllByStageAndCreatedAtBefore(String stage, Instant threshold);
}

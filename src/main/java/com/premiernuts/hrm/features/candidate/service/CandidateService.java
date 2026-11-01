package com.premiernuts.hrm.features.candidate.service;

import com.premiernuts.hrm.features.candidate.dto.CandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.CandidateSearchDTO;
import com.premiernuts.hrm.features.candidate.dto.ConvertCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.CreateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.UpdateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.UpdateCandidateStageDTO;
import org.springframework.data.domain.Page;

public interface CandidateService {

    Page<CandidateDTO> searchCandidates(CandidateSearchDTO searchDTO);

    CandidateDTO getCandidateById(Long id);

    CandidateDTO createCandidate(CreateCandidateDTO request);

    CandidateDTO updateCandidate(Long id, UpdateCandidateDTO request);

    CandidateDTO updateStage(Long id, UpdateCandidateStageDTO request);

    CandidateDTO convertToEmployee(Long id, ConvertCandidateDTO request);

    void deleteCandidate(Long id);
}

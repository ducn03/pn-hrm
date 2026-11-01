package com.premiernuts.hrm.features.candidate.service.impl;

import com.premiernuts.hrm.core.enums.ECandidateStage;
import com.premiernuts.hrm.core.enums.EStatus;
import com.premiernuts.hrm.core.exception.ErrorCodes;
import com.premiernuts.hrm.core.exception.PnException;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.candidate.dto.CandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.CandidateSearchDTO;
import com.premiernuts.hrm.features.candidate.dto.ConvertCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.CreateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.UpdateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.UpdateCandidateStageDTO;
import com.premiernuts.hrm.features.candidate.mapper.CandidateMapper;
import com.premiernuts.hrm.features.candidate.service.CandidateService;
import com.premiernuts.hrm.features.candidate.spec.CandidateSpecification;
import com.premiernuts.hrm.features.candidate.validator.CandidateValidator;
import com.premiernuts.hrm.jpa.domain.candidate.Candidate;
import com.premiernuts.hrm.jpa.repository.candidate.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;
    private final CandidateValidator candidateValidator;

    @Override
    @Transactional(readOnly = true)
    public Page<CandidateDTO> searchCandidates(CandidateSearchDTO searchDTO) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(searchDTO.getNormalizedPage(), searchDTO.getNormalizedSize(), sort);
        Page<Candidate> page = candidateRepository.findAll(CandidateSpecification.filter(searchDTO), pageable);
        return page.map(candidateMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public CandidateDTO getCandidateById(Long id) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        Optional<Candidate> candidateOpt = candidateRepository.findById(id);
        if (candidateOpt.isEmpty()) {
            throw new PnException(ErrorCodes.CANDIDATE.CANDIDATE_NOT_FOUND);
        }

        return candidateMapper.toDTO(candidateOpt.get());
    }

    @Override
    @Transactional
    public CandidateDTO createCandidate(CreateCandidateDTO request) {
        candidateValidator.validateCreate(request);

        Candidate entity = candidateMapper.toEntity(request);
        Candidate saved = candidateRepository.save(entity);
        return candidateMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public CandidateDTO updateCandidate(Long id, UpdateCandidateDTO request) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        candidateValidator.validateUpdate(request);

        Optional<Candidate> candidateOpt = candidateRepository.findById(id);
        if (candidateOpt.isEmpty()) {
            throw new PnException(ErrorCodes.CANDIDATE.CANDIDATE_NOT_FOUND);
        }

        Candidate entity = candidateOpt.get();
        candidateMapper.updateEntity(entity, request);
        Candidate updated = candidateRepository.save(entity);
        return candidateMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public CandidateDTO updateStage(Long id, UpdateCandidateStageDTO request) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        candidateValidator.validateStageUpdate(request);

        Optional<Candidate> candidateOpt = candidateRepository.findById(id);
        if (candidateOpt.isEmpty()) {
            throw new PnException(ErrorCodes.CANDIDATE.CANDIDATE_NOT_FOUND);
        }

        Candidate entity = candidateOpt.get();
        entity.setStage(StringHelper.trim(request.getStage()).toUpperCase());

        if (request.getInterviewNote() != null) {
            entity.setInterviewNote(StringHelper.trimToNull(request.getInterviewNote()));
        }
        if (request.getInterviewScore() != null) {
            entity.setInterviewScore(request.getInterviewScore());
        }

        Candidate updated = candidateRepository.save(entity);
        return candidateMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public CandidateDTO convertToEmployee(Long id, ConvertCandidateDTO request) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        candidateValidator.validateConvert(request);

        Optional<Candidate> candidateOpt = candidateRepository.findById(id);
        if (candidateOpt.isEmpty()) {
            throw new PnException(ErrorCodes.CANDIDATE.CANDIDATE_NOT_FOUND);
        }

        Candidate entity = candidateOpt.get();
        if (entity.getConvertedUserId() != null) {
            throw new PnException(ErrorCodes.CANDIDATE.ALREADY_CONVERTED);
        }

        entity.setStage(ECandidateStage.PASSED.name());
        entity.setConvertedUserId(request.getConvertedUserId());

        Candidate updated = candidateRepository.save(entity);
        return candidateMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void deleteCandidate(Long id) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        Optional<Candidate> candidateOpt = candidateRepository.findById(id);
        if (candidateOpt.isEmpty()) {
            throw new PnException(ErrorCodes.CANDIDATE.CANDIDATE_NOT_FOUND);
        }

        Candidate entity = candidateOpt.get();
        entity.setStatus(EStatus.DELETED.name());
        candidateRepository.save(entity);
    }
}

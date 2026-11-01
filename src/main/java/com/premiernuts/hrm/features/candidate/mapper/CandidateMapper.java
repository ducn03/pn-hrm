package com.premiernuts.hrm.features.candidate.mapper;

import com.premiernuts.hrm.core.enums.ECandidateStage;
import com.premiernuts.hrm.core.enums.EStatus;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.candidate.dto.CreateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.UpdateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.CandidateDTO;
import com.premiernuts.hrm.jpa.domain.candidate.Candidate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class CandidateMapper {

    public CandidateDTO toDTO(Candidate entity) {
        if (entity == null) {
            return null;
        }

        CandidateDTO dto = new CandidateDTO();
        dto.setId(entity.getId());
        dto.setCompanyId(entity.getCompanyId());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setRoleId(entity.getRoleId());
        dto.setFullName(entity.getFullName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setDob(entity.getDob());
        dto.setGender(entity.getGender());
        dto.setCitizenId(entity.getCitizenId());
        dto.setCvUrl(entity.getCvUrl());
        dto.setSource(entity.getSource());
        dto.setInterviewDate(entity.getInterviewDate());
        dto.setInterviewerId(entity.getInterviewerId());
        dto.setInterviewNote(entity.getInterviewNote());
        dto.setInterviewScore(entity.getInterviewScore());
        dto.setExpectedSalary(entity.getExpectedSalary());
        dto.setStage(entity.getStage());
        dto.setConvertedUserId(entity.getConvertedUserId());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public List<CandidateDTO> toDTOList(List<Candidate> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        List<CandidateDTO> dtos = new ArrayList<>();
        for (Candidate entity : entities) {
            CandidateDTO dto = toDTO(entity);
            if (dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public Candidate toEntity(CreateCandidateDTO request) {
        if (request == null) {
            return null;
        }

        Candidate entity = new Candidate();
        entity.setCompanyId(request.getCompanyId());
        entity.setDepartmentId(request.getDepartmentId());
        entity.setRoleId(request.getRoleId());
        entity.setFullName(StringHelper.trim(request.getFullName()));
        entity.setPhone(StringHelper.trim(request.getPhone()));
        entity.setEmail(StringHelper.trimToNull(request.getEmail()));
        entity.setDob(request.getDob());

        if (StringHelper.isNotBlank(request.getGender())) {
            entity.setGender(StringHelper.trim(request.getGender()).toUpperCase());
        }
        entity.setCitizenId(StringHelper.trimToNull(request.getCitizenId()));
        entity.setCvUrl(StringHelper.trimToNull(request.getCvUrl()));

        if (StringHelper.isNotBlank(request.getSource())) {
            entity.setSource(StringHelper.trim(request.getSource()).toUpperCase());
        } else {
            entity.setSource("DIRECT");
        }

        entity.setInterviewDate(request.getInterviewDate());
        entity.setInterviewerId(request.getInterviewerId());
        entity.setInterviewNote(StringHelper.trimToNull(request.getInterviewNote()));
        entity.setInterviewScore(request.getInterviewScore());
        entity.setExpectedSalary(request.getExpectedSalary());

        if (StringHelper.isNotBlank(request.getStage())) {
            entity.setStage(StringHelper.trim(request.getStage()).toUpperCase());
        } else {
            entity.setStage(ECandidateStage.APPLIED.name());
        }

        entity.setStatus(EStatus.ACTIVE.name());
        return entity;
    }

    public void updateEntity(Candidate entity, UpdateCandidateDTO request) {
        if (entity == null || request == null) {
            return;
        }

        if (request.getDepartmentId() != null) {
            entity.setDepartmentId(request.getDepartmentId());
        }
        if (request.getRoleId() != null) {
            entity.setRoleId(request.getRoleId());
        }
        if (StringHelper.isNotBlank(request.getFullName())) {
            entity.setFullName(StringHelper.trim(request.getFullName()));
        }
        if (StringHelper.isNotBlank(request.getPhone())) {
            entity.setPhone(StringHelper.trim(request.getPhone()));
        }
        if (StringHelper.isNotBlank(request.getEmail())) {
            entity.setEmail(StringHelper.trim(request.getEmail()));
        }
        if (request.getDob() != null) {
            entity.setDob(request.getDob());
        }
        if (StringHelper.isNotBlank(request.getGender())) {
            entity.setGender(StringHelper.trim(request.getGender()).toUpperCase());
        }
        if (StringHelper.isNotBlank(request.getCitizenId())) {
            entity.setCitizenId(StringHelper.trim(request.getCitizenId()));
        }
        if (StringHelper.isNotBlank(request.getCvUrl())) {
            entity.setCvUrl(StringHelper.trim(request.getCvUrl()));
        }
        if (StringHelper.isNotBlank(request.getSource())) {
            entity.setSource(StringHelper.trim(request.getSource()).toUpperCase());
        }
        if (request.getInterviewDate() != null) {
            entity.setInterviewDate(request.getInterviewDate());
        }
        if (request.getInterviewerId() != null) {
            entity.setInterviewerId(request.getInterviewerId());
        }
        if (request.getInterviewNote() != null) {
            entity.setInterviewNote(StringHelper.trimToNull(request.getInterviewNote()));
        }
        if (request.getInterviewScore() != null) {
            entity.setInterviewScore(request.getInterviewScore());
        }
        if (request.getExpectedSalary() != null) {
            entity.setExpectedSalary(request.getExpectedSalary());
        }
        if (StringHelper.isNotBlank(request.getStage())) {
            entity.setStage(StringHelper.trim(request.getStage()).toUpperCase());
        }
        if (StringHelper.isNotBlank(request.getStatus())) {
            entity.setStatus(StringHelper.trim(request.getStatus()).toUpperCase());
        }
    }
}

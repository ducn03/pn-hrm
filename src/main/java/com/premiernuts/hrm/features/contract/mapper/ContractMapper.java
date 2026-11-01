package com.premiernuts.hrm.features.contract.mapper;

import com.premiernuts.hrm.core.enums.EStatus;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.contract.dto.CreateUserContractDTO;
import com.premiernuts.hrm.features.contract.dto.UpdateUserContractDTO;
import com.premiernuts.hrm.features.contract.dto.UserContractDTO;
import com.premiernuts.hrm.jpa.domain.contract.UserContract;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ContractMapper {

    public UserContractDTO toDTO(UserContract entity) {
        if (entity == null) {
            return null;
        }

        UserContractDTO dto = new UserContractDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setContractNumber(entity.getContractNumber());
        dto.setContractType(entity.getContractType());
        dto.setStartDate(entity.getStartDate());
        dto.setEndDate(entity.getEndDate());
        dto.setSignedDate(entity.getSignedDate());
        dto.setBaseSalary(entity.getBaseSalary());
        dto.setAllowance(entity.getAllowance());
        dto.setNote(entity.getNote());
        dto.setAttachmentUrl(entity.getAttachmentUrl());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public List<UserContractDTO> toDTOList(List<UserContract> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        List<UserContractDTO> dtos = new ArrayList<>();
        for (UserContract entity : entities) {
            UserContractDTO dto = toDTO(entity);
            if (dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public UserContract toEntity(CreateUserContractDTO request) {
        if (request == null) {
            return null;
        }

        UserContract entity = new UserContract();
        entity.setUserId(request.getUserId());
        entity.setContractNumber(StringHelper.trim(request.getContractNumber()));
        entity.setContractType(StringHelper.trim(request.getContractType()).toUpperCase());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setSignedDate(request.getSignedDate());

        if (request.getBaseSalary() != null) {
            entity.setBaseSalary(request.getBaseSalary());
        } else {
            entity.setBaseSalary(BigDecimal.ZERO);
        }

        if (request.getAllowance() != null) {
            entity.setAllowance(request.getAllowance());
        } else {
            entity.setAllowance(BigDecimal.ZERO);
        }

        entity.setNote(StringHelper.trimToNull(request.getNote()));
        entity.setAttachmentUrl(StringHelper.trimToNull(request.getAttachmentUrl()));
        entity.setStatus(EStatus.ACTIVE.name());
        return entity;
    }

    public void updateEntity(UserContract entity, UpdateUserContractDTO request) {
        if (entity == null || request == null) {
            return;
        }

        if (StringHelper.isNotBlank(request.getContractType())) {
            entity.setContractType(StringHelper.trim(request.getContractType()).toUpperCase());
        }
        if (request.getStartDate() != null) {
            entity.setStartDate(request.getStartDate());
        }
        if (request.getEndDate() != null) {
            entity.setEndDate(request.getEndDate());
        }
        if (request.getSignedDate() != null) {
            entity.setSignedDate(request.getSignedDate());
        }
        if (request.getBaseSalary() != null) {
            entity.setBaseSalary(request.getBaseSalary());
        }
        if (request.getAllowance() != null) {
            entity.setAllowance(request.getAllowance());
        }
        entity.setNote(StringHelper.trimToNull(request.getNote()));
        entity.setAttachmentUrl(StringHelper.trimToNull(request.getAttachmentUrl()));
        if (StringHelper.isNotBlank(request.getStatus())) {
            entity.setStatus(StringHelper.trim(request.getStatus()).toUpperCase());
        }
    }
}

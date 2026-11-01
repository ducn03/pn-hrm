package com.premiernuts.hrm.features.employee.mapper;

import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.employee.dto.EmployeeDTO;
import com.premiernuts.hrm.features.employee.dto.UpdateEmployeeDTO;
import com.premiernuts.hrm.jpa.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class EmployeeMapper {

    public EmployeeDTO toDTO(User entity) {
        if (entity == null) {
            return null;
        }

        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(entity.getId());
        dto.setCompanyId(entity.getCompanyId());
        dto.setDepartmentId(entity.getDepartmentId());
        dto.setRoleId(entity.getRoleId());
        dto.setUsername(entity.getUsername());
        dto.setFullName(entity.getFullName());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setAvatarUrl(entity.getAvatarUrl());
        dto.setGender(entity.getGender());
        dto.setDob(entity.getDob());
        dto.setCitizenId(entity.getCitizenId());
        dto.setCitizenIssueDate(entity.getCitizenIssueDate());
        dto.setCitizenIssuePlace(entity.getCitizenIssuePlace());
        dto.setHometown(entity.getHometown());
        dto.setPermanentAddress(entity.getPermanentAddress());
        dto.setCurrentAddress(entity.getCurrentAddress());
        dto.setEmergencyContactName(entity.getEmergencyContactName());
        dto.setEmergencyContactPhone(entity.getEmergencyContactPhone());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        return dto;
    }

    public List<EmployeeDTO> toDTOList(List<User> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        List<EmployeeDTO> dtos = new ArrayList<>();
        for (User entity : entities) {
            EmployeeDTO dto = toDTO(entity);
            if (dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }

    public void updateEntity(User entity, UpdateEmployeeDTO request) {
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
        if (StringHelper.isNotBlank(request.getEmail())) {
            entity.setEmail(StringHelper.trim(request.getEmail()));
        }
        if (StringHelper.isNotBlank(request.getPhone())) {
            entity.setPhone(StringHelper.trim(request.getPhone()));
        }
        if (StringHelper.isNotBlank(request.getAvatarUrl())) {
            entity.setAvatarUrl(StringHelper.trim(request.getAvatarUrl()));
        }
        if (StringHelper.isNotBlank(request.getGender())) {
            entity.setGender(StringHelper.trim(request.getGender()).toUpperCase());
        }
        if (request.getDob() != null) {
            entity.setDob(request.getDob());
        }
        if (StringHelper.isNotBlank(request.getCitizenId())) {
            entity.setCitizenId(StringHelper.trim(request.getCitizenId()));
        }
        if (request.getCitizenIssueDate() != null) {
            entity.setCitizenIssueDate(request.getCitizenIssueDate());
        }
        if (StringHelper.isNotBlank(request.getCitizenIssuePlace())) {
            entity.setCitizenIssuePlace(StringHelper.trim(request.getCitizenIssuePlace()));
        }
        if (StringHelper.isNotBlank(request.getHometown())) {
            entity.setHometown(StringHelper.trim(request.getHometown()));
        }
        if (StringHelper.isNotBlank(request.getPermanentAddress())) {
            entity.setPermanentAddress(StringHelper.trim(request.getPermanentAddress()));
        }
        if (StringHelper.isNotBlank(request.getCurrentAddress())) {
            entity.setCurrentAddress(StringHelper.trim(request.getCurrentAddress()));
        }
        if (StringHelper.isNotBlank(request.getEmergencyContactName())) {
            entity.setEmergencyContactName(StringHelper.trim(request.getEmergencyContactName()));
        }
        if (StringHelper.isNotBlank(request.getEmergencyContactPhone())) {
            entity.setEmergencyContactPhone(StringHelper.trim(request.getEmergencyContactPhone()));
        }
        if (StringHelper.isNotBlank(request.getStatus())) {
            entity.setStatus(StringHelper.trim(request.getStatus()).toUpperCase());
        }
    }
}

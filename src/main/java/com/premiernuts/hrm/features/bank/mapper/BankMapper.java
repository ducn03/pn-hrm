package com.premiernuts.hrm.features.bank.mapper;

import com.premiernuts.hrm.features.bank.dto.BankDTO;
import com.premiernuts.hrm.jpa.domain.bank.Bank;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class BankMapper {

    public BankDTO toDTO(Bank entity) {
        if (entity == null) {
            return null;
        }

        BankDTO dto = new BankDTO();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setName(entity.getName());
        dto.setShortName(entity.getShortName());
        dto.setBin(entity.getBin());
        dto.setStatus(entity.getStatus());
        return dto;
    }

    public List<BankDTO> toDTOList(List<Bank> entities) {
        if (entities == null || entities.isEmpty()) {
            return Collections.emptyList();
        }

        List<BankDTO> dtos = new ArrayList<>();
        for (Bank entity : entities) {
            BankDTO dto = toDTO(entity);
            if (dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }
}

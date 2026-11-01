package com.premiernuts.hrm.features.finance.mapper;

import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.finance.dto.UpdateUserFinanceDTO;
import com.premiernuts.hrm.features.finance.dto.UserFinanceDTO;
import com.premiernuts.hrm.jpa.domain.bank.Bank;
import com.premiernuts.hrm.jpa.domain.finance.UserFinance;
import org.springframework.stereotype.Component;

@Component
public class FinanceMapper {

    public UserFinanceDTO toDTO(UserFinance entity, Bank bank) {
        if (entity == null) {
            return null;
        }

        UserFinanceDTO dto = new UserFinanceDTO();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setBankId(entity.getBankId());
        dto.setBankAccountNo(entity.getBankAccountNo());
        dto.setBankBranch(entity.getBankBranch());
        dto.setTaxCode(entity.getTaxCode());
        dto.setDependentsCount(entity.getDependentsCount());
        dto.setSocialInsuranceNo(entity.getSocialInsuranceNo());
        dto.setHealthInsuranceNo(entity.getHealthInsuranceNo());
        dto.setStatus(entity.getStatus());

        if (bank != null) {
            dto.setBankCode(bank.getCode());
            dto.setBankName(bank.getName());
            dto.setBankShortName(bank.getShortName());
        }

        return dto;
    }

    public void updateEntity(UserFinance entity, UpdateUserFinanceDTO request) {
        if (entity == null || request == null) {
            return;
        }

        entity.setBankId(request.getBankId());
        entity.setBankAccountNo(StringHelper.trimToNull(request.getBankAccountNo()));
        entity.setBankBranch(StringHelper.trimToNull(request.getBankBranch()));
        entity.setTaxCode(StringHelper.trimToNull(request.getTaxCode()));
        if (request.getDependentsCount() != null) {
            entity.setDependentsCount(request.getDependentsCount());
        }
        entity.setSocialInsuranceNo(StringHelper.trimToNull(request.getSocialInsuranceNo()));
        entity.setHealthInsuranceNo(StringHelper.trimToNull(request.getHealthInsuranceNo()));
    }
}

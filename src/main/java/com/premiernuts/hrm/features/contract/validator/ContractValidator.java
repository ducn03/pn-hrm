package com.premiernuts.hrm.features.contract.validator;

import com.premiernuts.hrm.core.enums.EContractType;
import com.premiernuts.hrm.core.exception.ErrorCodes;
import com.premiernuts.hrm.core.exception.PnException;
import com.premiernuts.hrm.core.utils.MessageHelper;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.contract.dto.CreateUserContractDTO;
import com.premiernuts.hrm.features.contract.dto.UpdateUserContractDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContractValidator {

    public void validateCreate(CreateUserContractDTO request) {
        if (request == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        List<String> errors = new ArrayList<>();

        if (request.getUserId() == null) {
            errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.USER_ID_REQUIRED));
        }

        if (StringHelper.isBlank(request.getContractNumber())) {
            errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.CONTRACT_NUMBER_REQUIRED));
        }

        if (StringHelper.isBlank(request.getContractType())) {
            errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.INVALID_CONTRACT_TYPE));
        } else {
            if (!isValidContractType(request.getContractType())) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.INVALID_CONTRACT_TYPE));
            }
        }

        if (request.getStartDate() == null) {
            errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.START_DATE_REQUIRED));
        }

        if (request.getStartDate() != null && request.getEndDate() != null) {
            if (request.getEndDate().isBefore(request.getStartDate())) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.INVALID_DATE_RANGE));
            }
        }

        if (request.getBaseSalary() != null) {
            if (request.getBaseSalary().compareTo(BigDecimal.ZERO) < 0) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.SALARY_NEGATIVE));
            }
        }

        if (request.getAllowance() != null) {
            if (request.getAllowance().compareTo(BigDecimal.ZERO) < 0) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.ALLOWANCE_NEGATIVE));
            }
        }

        if (!errors.isEmpty()) {
            throw new PnException(ErrorCodes.VALIDATION.INVALID_INPUT, errors);
        }
    }

    public void validateUpdate(UpdateUserContractDTO request) {
        if (request == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        List<String> errors = new ArrayList<>();

        if (StringHelper.isNotBlank(request.getContractType())) {
            if (!isValidContractType(request.getContractType())) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.INVALID_CONTRACT_TYPE));
            }
        }

        if (request.getStartDate() != null && request.getEndDate() != null) {
            if (request.getEndDate().isBefore(request.getStartDate())) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.INVALID_DATE_RANGE));
            }
        }

        if (request.getBaseSalary() != null) {
            if (request.getBaseSalary().compareTo(BigDecimal.ZERO) < 0) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.SALARY_NEGATIVE));
            }
        }

        if (request.getAllowance() != null) {
            if (request.getAllowance().compareTo(BigDecimal.ZERO) < 0) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CONTRACT.ALLOWANCE_NEGATIVE));
            }
        }

        if (!errors.isEmpty()) {
            throw new PnException(ErrorCodes.VALIDATION.INVALID_INPUT, errors);
        }
    }

    private boolean isValidContractType(String type) {
        if (StringHelper.isBlank(type)) {
            return false;
        }
        for (EContractType contractType : EContractType.values()) {
            if (contractType.name().equalsIgnoreCase(StringHelper.trim(type))) {
                return true;
            }
        }
        return false;
    }
}

package com.premiernuts.hrm.features.candidate.validator;

import com.premiernuts.hrm.core.enums.ECandidateStage;
import com.premiernuts.hrm.core.exception.ErrorCodes;
import com.premiernuts.hrm.core.exception.PnException;
import com.premiernuts.hrm.core.utils.MessageHelper;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.candidate.dto.ConvertCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.CreateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.UpdateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.UpdateCandidateStageDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CandidateValidator {

    public void validateCreate(CreateCandidateDTO request) {
        if (request == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        List<String> errors = new ArrayList<>();

        if (request.getCompanyId() == null) {
            errors.add(MessageHelper.getMessage("40002001"));
        }

        if (StringHelper.isBlank(request.getFullName())) {
            errors.add(MessageHelper.getMessage(ErrorCodes.VALIDATION.FULL_NAME_REQUIRED));
        }

        if (StringHelper.isBlank(request.getPhone())) {
            errors.add(MessageHelper.getMessage(ErrorCodes.CANDIDATE.INVALID_PHONE));
        } else {
            String trimmedPhone = StringHelper.trim(request.getPhone());
            if (trimmedPhone.length() < 9 || trimmedPhone.length() > 20) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CANDIDATE.INVALID_PHONE));
            }
        }

        if (StringHelper.isNotBlank(request.getEmail())) {
            String trimmedEmail = StringHelper.trim(request.getEmail());
            if (!trimmedEmail.contains("@") || !trimmedEmail.contains(".")) {
                errors.add(MessageHelper.getMessage(ErrorCodes.VALIDATION.EMAIL_INVALID));
            }
        }

        if (StringHelper.isNotBlank(request.getStage())) {
            if (!isValidStage(request.getStage())) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CANDIDATE.INVALID_STAGE));
            }
        }

        if (!errors.isEmpty()) {
            throw new PnException(ErrorCodes.VALIDATION.INVALID_INPUT, errors);
        }
    }

    public void validateUpdate(UpdateCandidateDTO request) {
        if (request == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        List<String> errors = new ArrayList<>();

        if (StringHelper.isNotBlank(request.getFullName())) {
            String trimmedName = StringHelper.trim(request.getFullName());
            if (trimmedName.isEmpty()) {
                errors.add(MessageHelper.getMessage(ErrorCodes.VALIDATION.FULL_NAME_REQUIRED));
            }
        }

        if (StringHelper.isNotBlank(request.getPhone())) {
            String trimmedPhone = StringHelper.trim(request.getPhone());
            if (trimmedPhone.length() < 9 || trimmedPhone.length() > 20) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CANDIDATE.INVALID_PHONE));
            }
        }

        if (StringHelper.isNotBlank(request.getEmail())) {
            String trimmedEmail = StringHelper.trim(request.getEmail());
            if (!trimmedEmail.contains("@") || !trimmedEmail.contains(".")) {
                errors.add(MessageHelper.getMessage(ErrorCodes.VALIDATION.EMAIL_INVALID));
            }
        }

        if (StringHelper.isNotBlank(request.getStage())) {
            if (!isValidStage(request.getStage())) {
                errors.add(MessageHelper.getMessage(ErrorCodes.CANDIDATE.INVALID_STAGE));
            }
        }

        if (!errors.isEmpty()) {
            throw new PnException(ErrorCodes.VALIDATION.INVALID_INPUT, errors);
        }
    }

    public void validateStageUpdate(UpdateCandidateStageDTO request) {
        if (request == null || StringHelper.isBlank(request.getStage())) {
            throw new PnException(ErrorCodes.CANDIDATE.INVALID_STAGE);
        }

        if (!isValidStage(request.getStage())) {
            throw new PnException(ErrorCodes.CANDIDATE.INVALID_STAGE);
        }
    }

    public void validateConvert(ConvertCandidateDTO request) {
        if (request == null || request.getConvertedUserId() == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }
    }

    private boolean isValidStage(String stage) {
        if (StringHelper.isBlank(stage)) {
            return false;
        }
        for (ECandidateStage candidateStage : ECandidateStage.values()) {
            if (candidateStage.name().equalsIgnoreCase(StringHelper.trim(stage))) {
                return true;
            }
        }
        return false;
    }
}

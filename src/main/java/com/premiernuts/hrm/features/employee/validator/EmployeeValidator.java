package com.premiernuts.hrm.features.employee.validator;

import com.premiernuts.hrm.core.exception.ErrorCodes;
import com.premiernuts.hrm.core.exception.PnException;
import com.premiernuts.hrm.core.utils.MessageHelper;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.employee.dto.UpdateEmployeeDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeValidator {

    public void validateUpdate(UpdateEmployeeDTO request) {
        if (request == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        List<String> errors = new ArrayList<>();

        if (StringHelper.isNotBlank(request.getFullName())) {
            String trimmedName = StringHelper.trim(request.getFullName());
            if (trimmedName.isEmpty()) {
                errors.add(MessageHelper.getMessage(ErrorCodes.EMPLOYEE.FULL_NAME_REQUIRED));
            }
        }

        if (StringHelper.isNotBlank(request.getPhone())) {
            String trimmedPhone = StringHelper.trim(request.getPhone());
            if (trimmedPhone.length() < 9 || trimmedPhone.length() > 20) {
                errors.add(MessageHelper.getMessage(ErrorCodes.EMPLOYEE.PHONE_INVALID));
            }
        }

        if (StringHelper.isNotBlank(request.getEmail())) {
            String trimmedEmail = StringHelper.trim(request.getEmail());
            if (!trimmedEmail.contains("@") || !trimmedEmail.contains(".")) {
                errors.add(MessageHelper.getMessage(ErrorCodes.EMPLOYEE.EMAIL_INVALID));
            }
        }

        if (StringHelper.isNotBlank(request.getCitizenId())) {
            String trimmedCitizenId = StringHelper.trim(request.getCitizenId());
            if (trimmedCitizenId.length() < 9 || trimmedCitizenId.length() > 20) {
                errors.add(MessageHelper.getMessage(ErrorCodes.EMPLOYEE.CITIZEN_ID_INVALID));
            }
        }

        if (!errors.isEmpty()) {
            throw new PnException(ErrorCodes.VALIDATION.INVALID_INPUT, errors);
        }
    }
}

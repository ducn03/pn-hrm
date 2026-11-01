package com.premiernuts.hrm.features.finance.validator;

import com.premiernuts.hrm.core.exception.ErrorCodes;
import com.premiernuts.hrm.core.exception.PnException;
import com.premiernuts.hrm.core.utils.MessageHelper;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.finance.dto.UpdateUserFinanceDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FinanceValidator {

    public void validateUpdate(UpdateUserFinanceDTO request) {
        if (request == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        List<String> errors = new ArrayList<>();

        if (request.getDependentsCount() != null) {
            if (request.getDependentsCount() < 0) {
                errors.add(MessageHelper.getMessage(ErrorCodes.FINANCE.DEPENDENTS_COUNT_NEGATIVE));
            }
        }

        if (StringHelper.isNotBlank(request.getBankAccountNo())) {
            String trimmedAccountNo = StringHelper.trim(request.getBankAccountNo());
            if (trimmedAccountNo.length() < 6 || trimmedAccountNo.length() > 30) {
                errors.add(MessageHelper.getMessage(ErrorCodes.FINANCE.INVALID_ACCOUNT_NO));
            }
        }

        if (!errors.isEmpty()) {
            throw new PnException(ErrorCodes.VALIDATION.INVALID_INPUT, errors);
        }
    }
}

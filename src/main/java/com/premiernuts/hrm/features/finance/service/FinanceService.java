package com.premiernuts.hrm.features.finance.service;

import com.premiernuts.hrm.features.finance.dto.UpdateUserFinanceDTO;
import com.premiernuts.hrm.features.finance.dto.UserFinanceDTO;

public interface FinanceService {

    UserFinanceDTO getByUserId(Long userId);

    UserFinanceDTO updateByUserId(Long userId, UpdateUserFinanceDTO request);
}

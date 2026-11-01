package com.premiernuts.hrm.features.finance.controller;

import com.premiernuts.hrm.core.aop.LogActivity;
import com.premiernuts.hrm.core.dto.ResponseData;
import com.premiernuts.hrm.core.enums.EPermission;
import com.premiernuts.hrm.core.utils.ResponseUtils;
import com.premiernuts.hrm.features.finance.constants.FinanceEndpoints;
import com.premiernuts.hrm.features.finance.dto.UpdateUserFinanceDTO;
import com.premiernuts.hrm.features.finance.dto.UserFinanceDTO;
import com.premiernuts.hrm.features.finance.service.FinanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(FinanceEndpoints.BASE)
@RequiredArgsConstructor
public class FinanceController {

    private final FinanceService financeService;

    @GetMapping(FinanceEndpoints.BY_USER_ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_VIEW})
    public ResponseEntity<ResponseData<UserFinanceDTO>> getByUserId(@PathVariable Long userId) {
        UserFinanceDTO dto = financeService.getByUserId(userId);
        return ResponseUtils.toSuccess(dto);
    }

    @PutMapping(FinanceEndpoints.BY_USER_ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<UserFinanceDTO>> updateByUserId(@PathVariable Long userId,
                                                                      @RequestBody UpdateUserFinanceDTO request) {
        UserFinanceDTO dto = financeService.updateByUserId(userId, request);
        return ResponseUtils.toSuccess(dto);
    }
}

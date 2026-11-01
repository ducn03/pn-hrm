package com.premiernuts.hrm.features.bank.controller;

import com.premiernuts.hrm.core.aop.LogActivity;
import com.premiernuts.hrm.core.dto.ResponseData;
import com.premiernuts.hrm.core.utils.ResponseUtils;
import com.premiernuts.hrm.features.bank.constants.BankEndpoints;
import com.premiernuts.hrm.features.bank.dto.BankDTO;
import com.premiernuts.hrm.features.bank.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(BankEndpoints.BASE)
@RequiredArgsConstructor
public class BankController {

    private final BankService bankService;

    @GetMapping
    @LogActivity(requireAuth = true)
    public ResponseEntity<ResponseData<List<BankDTO>>> getActiveBanks() {
        List<BankDTO> banks = bankService.getActiveBanks();
        return ResponseUtils.toSuccess(banks);
    }
}

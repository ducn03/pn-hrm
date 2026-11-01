package com.premiernuts.hrm.features.contract.controller;

import com.premiernuts.hrm.core.aop.LogActivity;
import com.premiernuts.hrm.core.dto.ResponseData;
import com.premiernuts.hrm.core.enums.EPermission;
import com.premiernuts.hrm.core.utils.ResponseUtils;
import com.premiernuts.hrm.features.contract.constants.ContractEndpoints;
import com.premiernuts.hrm.features.contract.dto.CreateUserContractDTO;
import com.premiernuts.hrm.features.contract.dto.UpdateUserContractDTO;
import com.premiernuts.hrm.features.contract.dto.UserContractDTO;
import com.premiernuts.hrm.features.contract.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(ContractEndpoints.BASE)
@RequiredArgsConstructor
public class ContractController {

    private final ContractService contractService;

    @GetMapping(ContractEndpoints.BY_USER)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_VIEW})
    public ResponseEntity<ResponseData<List<UserContractDTO>>> getContractsByUserId(@PathVariable Long userId) {
        List<UserContractDTO> contracts = contractService.getContractsByUserId(userId);
        return ResponseUtils.toSuccess(contracts);
    }

    @GetMapping(ContractEndpoints.ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_VIEW})
    public ResponseEntity<ResponseData<UserContractDTO>> getContractById(@PathVariable Long id) {
        UserContractDTO contract = contractService.getContractById(id);
        return ResponseUtils.toSuccess(contract);
    }

    @PostMapping
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<UserContractDTO>> createContract(@RequestBody CreateUserContractDTO request) {
        UserContractDTO created = contractService.createContract(request);
        return ResponseUtils.toSuccess(created);
    }

    @PutMapping(ContractEndpoints.ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<UserContractDTO>> updateContract(@PathVariable Long id,
                                                                        @RequestBody UpdateUserContractDTO request) {
        UserContractDTO updated = contractService.updateContract(id, request);
        return ResponseUtils.toSuccess(updated);
    }

    @DeleteMapping(ContractEndpoints.ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<Void>> deleteContract(@PathVariable Long id) {
        contractService.deleteContract(id);
        return ResponseUtils.toSuccess(null);
    }
}

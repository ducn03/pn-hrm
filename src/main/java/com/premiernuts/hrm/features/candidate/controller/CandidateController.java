package com.premiernuts.hrm.features.candidate.controller;

import com.premiernuts.hrm.core.aop.LogActivity;
import com.premiernuts.hrm.core.dto.PagingDTO;
import com.premiernuts.hrm.core.dto.ResponseData;
import com.premiernuts.hrm.core.enums.EPermission;
import com.premiernuts.hrm.core.security.UserContext;
import com.premiernuts.hrm.core.security.UserPrincipal;
import com.premiernuts.hrm.core.utils.ResponseUtils;
import com.premiernuts.hrm.features.candidate.constants.CandidateEndpoints;
import com.premiernuts.hrm.features.candidate.dto.CandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.CandidateSearchDTO;
import com.premiernuts.hrm.features.candidate.dto.ConvertCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.CreateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.UpdateCandidateDTO;
import com.premiernuts.hrm.features.candidate.dto.UpdateCandidateStageDTO;
import com.premiernuts.hrm.features.candidate.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CandidateEndpoints.BASE)
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @GetMapping
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_VIEW})
    public ResponseEntity<ResponseData<List<CandidateDTO>>> searchCandidates(@ModelAttribute CandidateSearchDTO searchDTO) {
        UserPrincipal principal = UserContext.get();
        if (principal != null && searchDTO.getCompanyId() == null) {
            searchDTO.setCompanyId(principal.getCompanyId());
        }

        Page<CandidateDTO> page = candidateService.searchCandidates(searchDTO);
        PagingDTO paging = new PagingDTO(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
        return ResponseUtils.toSuccessArray(page.getContent(), paging);
    }

    @GetMapping(CandidateEndpoints.ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_VIEW})
    public ResponseEntity<ResponseData<CandidateDTO>> getCandidateById(@PathVariable Long id) {
        CandidateDTO dto = candidateService.getCandidateById(id);
        return ResponseUtils.toSuccess(dto);
    }

    @PostMapping
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<CandidateDTO>> createCandidate(@RequestBody CreateCandidateDTO request) {
        UserPrincipal principal = UserContext.get();
        if (principal != null && request.getCompanyId() == null) {
            request.setCompanyId(principal.getCompanyId());
        }

        CandidateDTO created = candidateService.createCandidate(request);
        return ResponseUtils.toSuccess(created);
    }

    @PutMapping(CandidateEndpoints.ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<CandidateDTO>> updateCandidate(@PathVariable Long id,
                                                                      @RequestBody UpdateCandidateDTO request) {
        CandidateDTO updated = candidateService.updateCandidate(id, request);
        return ResponseUtils.toSuccess(updated);
    }

    @PatchMapping(CandidateEndpoints.STAGE)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<CandidateDTO>> updateStage(@PathVariable Long id,
                                                                 @RequestBody UpdateCandidateStageDTO request) {
        CandidateDTO updated = candidateService.updateStage(id, request);
        return ResponseUtils.toSuccess(updated);
    }

    @PostMapping(CandidateEndpoints.CONVERT)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<CandidateDTO>> convertToEmployee(@PathVariable Long id,
                                                                        @RequestBody ConvertCandidateDTO request) {
        CandidateDTO converted = candidateService.convertToEmployee(id, request);
        return ResponseUtils.toSuccess(converted);
    }

    @DeleteMapping(CandidateEndpoints.ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<Void>> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseUtils.toSuccess(null);
    }
}

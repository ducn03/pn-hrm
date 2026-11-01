package com.premiernuts.hrm.features.employee.controller;

import com.premiernuts.hrm.core.aop.LogActivity;
import com.premiernuts.hrm.core.dto.PagingDTO;
import com.premiernuts.hrm.core.dto.ResponseData;
import com.premiernuts.hrm.core.enums.EPermission;
import com.premiernuts.hrm.core.security.UserContext;
import com.premiernuts.hrm.core.security.UserPrincipal;
import com.premiernuts.hrm.core.utils.ResponseUtils;
import com.premiernuts.hrm.features.employee.constants.EmployeeEndpoints;
import com.premiernuts.hrm.features.employee.dto.EmployeeDTO;
import com.premiernuts.hrm.features.employee.dto.EmployeeSearchDTO;
import com.premiernuts.hrm.features.employee.dto.UpdateEmployeeDTO;
import com.premiernuts.hrm.features.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(EmployeeEndpoints.BASE)
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_VIEW})
    public ResponseEntity<ResponseData<List<EmployeeDTO>>> searchEmployees(@ModelAttribute EmployeeSearchDTO searchDTO) {
        UserPrincipal principal = UserContext.get();
        if (principal != null && searchDTO.getCompanyId() == null) {
            searchDTO.setCompanyId(principal.getCompanyId());
        }

        Page<EmployeeDTO> page = employeeService.searchEmployees(searchDTO);
        PagingDTO paging = new PagingDTO(
                page.getNumber(),
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
        return ResponseUtils.toSuccessArray(page.getContent(), paging);
    }

    @GetMapping(EmployeeEndpoints.ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_VIEW})
    public ResponseEntity<ResponseData<EmployeeDTO>> getEmployeeById(@PathVariable Long id) {
        EmployeeDTO dto = employeeService.getEmployeeById(id);
        return ResponseUtils.toSuccess(dto);
    }

    @PutMapping(EmployeeEndpoints.ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<EmployeeDTO>> updateEmployee(@PathVariable Long id,
                                                                    @RequestBody UpdateEmployeeDTO request) {
        EmployeeDTO updated = employeeService.updateEmployee(id, request);
        return ResponseUtils.toSuccess(updated);
    }

    @DeleteMapping(EmployeeEndpoints.ID)
    @LogActivity(requireAuth = true, permissions = {EPermission.Names.HRM_MANAGE})
    public ResponseEntity<ResponseData<Void>> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseUtils.toSuccess(null);
    }
}

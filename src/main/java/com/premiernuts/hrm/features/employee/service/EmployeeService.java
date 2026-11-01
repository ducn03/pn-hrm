package com.premiernuts.hrm.features.employee.service;

import com.premiernuts.hrm.features.employee.dto.EmployeeDTO;
import com.premiernuts.hrm.features.employee.dto.EmployeeSearchDTO;
import com.premiernuts.hrm.features.employee.dto.UpdateEmployeeDTO;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    Page<EmployeeDTO> searchEmployees(EmployeeSearchDTO searchDTO);

    EmployeeDTO getEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id, UpdateEmployeeDTO request);

    void deleteEmployee(Long id);
}

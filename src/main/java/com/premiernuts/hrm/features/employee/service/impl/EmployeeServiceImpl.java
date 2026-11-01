package com.premiernuts.hrm.features.employee.service.impl;

import com.premiernuts.hrm.core.enums.EStatus;
import com.premiernuts.hrm.core.exception.ErrorCodes;
import com.premiernuts.hrm.core.exception.PnException;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.employee.dto.EmployeeDTO;
import com.premiernuts.hrm.features.employee.dto.EmployeeSearchDTO;
import com.premiernuts.hrm.features.employee.dto.UpdateEmployeeDTO;
import com.premiernuts.hrm.features.employee.mapper.EmployeeMapper;
import com.premiernuts.hrm.features.employee.service.EmployeeService;
import com.premiernuts.hrm.features.employee.spec.EmployeeSpecification;
import com.premiernuts.hrm.features.employee.validator.EmployeeValidator;
import com.premiernuts.hrm.jpa.domain.user.User;
import com.premiernuts.hrm.jpa.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final UserRepository userRepository;
    private final EmployeeMapper employeeMapper;
    private final EmployeeValidator employeeValidator;

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeDTO> searchEmployees(EmployeeSearchDTO searchDTO) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdAt");
        Pageable pageable = PageRequest.of(searchDTO.getNormalizedPage(), searchDTO.getNormalizedSize(), sort);
        Page<User> page = userRepository.findAll(EmployeeSpecification.filter(searchDTO), pageable);
        return page.map(employeeMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDTO getEmployeeById(Long id) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new PnException(ErrorCodes.EMPLOYEE.EMPLOYEE_NOT_FOUND);
        }

        return employeeMapper.toDTO(userOpt.get());
    }

    @Override
    @Transactional
    public EmployeeDTO updateEmployee(Long id, UpdateEmployeeDTO request) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        employeeValidator.validateUpdate(request);

        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new PnException(ErrorCodes.EMPLOYEE.EMPLOYEE_NOT_FOUND);
        }

        User user = userOpt.get();

        if (StringHelper.isNotBlank(request.getEmail())) {
            String trimmedEmail = StringHelper.trim(request.getEmail());
            Optional<User> existingEmailUser = userRepository.findByEmail(trimmedEmail);
            if (existingEmailUser.isPresent()) {
                User existing = existingEmailUser.get();
                if (!existing.getId().equals(user.getId())) {
                    throw new PnException(ErrorCodes.EMPLOYEE.EMAIL_EXISTS);
                }
            }
        }

        employeeMapper.updateEntity(user, request);
        User updated = userRepository.save(user);
        return employeeMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long id) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            throw new PnException(ErrorCodes.EMPLOYEE.EMPLOYEE_NOT_FOUND);
        }

        User user = userOpt.get();
        user.setStatus(EStatus.DELETED.name());
        userRepository.save(user);
    }
}

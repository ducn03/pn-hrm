package com.premiernuts.hrm.features.contract.service.impl;

import com.premiernuts.hrm.core.enums.EStatus;
import com.premiernuts.hrm.core.exception.ErrorCodes;
import com.premiernuts.hrm.core.exception.PnException;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.contract.dto.CreateUserContractDTO;
import com.premiernuts.hrm.features.contract.dto.UpdateUserContractDTO;
import com.premiernuts.hrm.features.contract.dto.UserContractDTO;
import com.premiernuts.hrm.features.contract.mapper.ContractMapper;
import com.premiernuts.hrm.features.contract.service.ContractService;
import com.premiernuts.hrm.features.contract.validator.ContractValidator;
import com.premiernuts.hrm.jpa.domain.contract.UserContract;
import com.premiernuts.hrm.jpa.repository.contract.UserContractRepository;
import com.premiernuts.hrm.jpa.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {

    private final UserContractRepository userContractRepository;
    private final UserRepository userRepository;
    private final ContractMapper contractMapper;
    private final ContractValidator contractValidator;

    @Override
    @Transactional(readOnly = true)
    public List<UserContractDTO> getContractsByUserId(Long userId) {
        if (userId == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        if (!userRepository.existsById(userId)) {
            throw new PnException(ErrorCodes.CONTRACT.USER_NOT_FOUND);
        }

        List<UserContract> contracts = userContractRepository.findAllByUserIdOrderByStartDateDesc(userId);
        return contractMapper.toDTOList(contracts);
    }

    @Override
    @Transactional(readOnly = true)
    public UserContractDTO getContractById(Long id) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        Optional<UserContract> contractOpt = userContractRepository.findById(id);
        if (contractOpt.isEmpty()) {
            throw new PnException(ErrorCodes.CONTRACT.CONTRACT_NOT_FOUND);
        }

        return contractMapper.toDTO(contractOpt.get());
    }

    @Override
    @Transactional
    public UserContractDTO createContract(CreateUserContractDTO request) {
        contractValidator.validateCreate(request);

        if (!userRepository.existsById(request.getUserId())) {
            throw new PnException(ErrorCodes.CONTRACT.USER_NOT_FOUND);
        }

        String trimmedNumber = StringHelper.trim(request.getContractNumber());
        if (userContractRepository.existsByContractNumber(trimmedNumber)) {
            throw new PnException(ErrorCodes.CONTRACT.CONTRACT_NUMBER_EXISTS);
        }

        UserContract entity = contractMapper.toEntity(request);
        UserContract saved = userContractRepository.save(entity);
        return contractMapper.toDTO(saved);
    }

    @Override
    @Transactional
    public UserContractDTO updateContract(Long id, UpdateUserContractDTO request) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        contractValidator.validateUpdate(request);

        Optional<UserContract> contractOpt = userContractRepository.findById(id);
        if (contractOpt.isEmpty()) {
            throw new PnException(ErrorCodes.CONTRACT.CONTRACT_NOT_FOUND);
        }

        UserContract entity = contractOpt.get();
        contractMapper.updateEntity(entity, request);
        UserContract updated = userContractRepository.save(entity);
        return contractMapper.toDTO(updated);
    }

    @Override
    @Transactional
    public void deleteContract(Long id) {
        if (id == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        Optional<UserContract> contractOpt = userContractRepository.findById(id);
        if (contractOpt.isEmpty()) {
            throw new PnException(ErrorCodes.CONTRACT.CONTRACT_NOT_FOUND);
        }

        UserContract entity = contractOpt.get();
        entity.setStatus(EStatus.DELETED.name());
        userContractRepository.save(entity);
    }
}

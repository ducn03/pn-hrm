package com.premiernuts.hrm.features.contract.service;

import com.premiernuts.hrm.features.contract.dto.CreateUserContractDTO;
import com.premiernuts.hrm.features.contract.dto.UpdateUserContractDTO;
import com.premiernuts.hrm.features.contract.dto.UserContractDTO;

import java.util.List;

public interface ContractService {

    List<UserContractDTO> getContractsByUserId(Long userId);

    UserContractDTO getContractById(Long id);

    UserContractDTO createContract(CreateUserContractDTO request);

    UserContractDTO updateContract(Long id, UpdateUserContractDTO request);

    void deleteContract(Long id);
}

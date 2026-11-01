package com.premiernuts.hrm.features.finance.service.impl;

import com.premiernuts.hrm.core.enums.EStatus;
import com.premiernuts.hrm.core.exception.ErrorCodes;
import com.premiernuts.hrm.core.exception.PnException;
import com.premiernuts.hrm.features.finance.dto.UpdateUserFinanceDTO;
import com.premiernuts.hrm.features.finance.dto.UserFinanceDTO;
import com.premiernuts.hrm.features.finance.mapper.FinanceMapper;
import com.premiernuts.hrm.features.finance.service.FinanceService;
import com.premiernuts.hrm.features.finance.validator.FinanceValidator;
import com.premiernuts.hrm.jpa.domain.bank.Bank;
import com.premiernuts.hrm.jpa.domain.finance.UserFinance;
import com.premiernuts.hrm.jpa.repository.bank.BankRepository;
import com.premiernuts.hrm.jpa.repository.finance.UserFinanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinanceServiceImpl implements FinanceService {

    private final UserFinanceRepository userFinanceRepository;
    private final BankRepository bankRepository;
    private final FinanceMapper financeMapper;
    private final FinanceValidator financeValidator;

    @Override
    @Transactional(readOnly = true)
    public UserFinanceDTO getByUserId(Long userId) {
        if (userId == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        Optional<UserFinance> financeOpt = userFinanceRepository.findByUserId(userId);
        if (financeOpt.isEmpty()) {
            UserFinanceDTO blankDTO = new UserFinanceDTO();
            blankDTO.setUserId(userId);
            blankDTO.setDependentsCount(0);
            blankDTO.setStatus(EStatus.ACTIVE.name());
            return blankDTO;
        }

        UserFinance finance = financeOpt.get();
        Bank bank = null;
        if (finance.getBankId() != null) {
            Optional<Bank> bankOpt = bankRepository.findById(finance.getBankId());
            if (bankOpt.isPresent()) {
                bank = bankOpt.get();
            }
        }

        return financeMapper.toDTO(finance, bank);
    }

    @Override
    @Transactional
    public UserFinanceDTO updateByUserId(Long userId, UpdateUserFinanceDTO request) {
        if (userId == null) {
            throw new PnException(ErrorCodes.COMMON.BAD_REQUEST);
        }

        financeValidator.validateUpdate(request);

        Bank bank = null;
        if (request.getBankId() != null) {
            Optional<Bank> bankOpt = bankRepository.findById(request.getBankId());
            if (bankOpt.isEmpty()) {
                throw new PnException(ErrorCodes.FINANCE.BANK_NOT_FOUND);
            }
            bank = bankOpt.get();
        }

        Optional<UserFinance> financeOpt = userFinanceRepository.findByUserId(userId);
        UserFinance finance;
        if (financeOpt.isPresent()) {
            finance = financeOpt.get();
        } else {
            finance = new UserFinance();
            finance.setUserId(userId);
            finance.setStatus(EStatus.ACTIVE.name());
        }

        financeMapper.updateEntity(finance, request);
        UserFinance saved = userFinanceRepository.save(finance);

        return financeMapper.toDTO(saved, bank);
    }
}

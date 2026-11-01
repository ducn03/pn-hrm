package com.premiernuts.hrm.features.bank.service.impl;

import com.premiernuts.hrm.core.enums.EStatus;
import com.premiernuts.hrm.features.bank.dto.BankDTO;
import com.premiernuts.hrm.features.bank.mapper.BankMapper;
import com.premiernuts.hrm.features.bank.service.BankService;
import com.premiernuts.hrm.jpa.domain.bank.Bank;
import com.premiernuts.hrm.jpa.repository.bank.BankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BankDTO> getActiveBanks() {
        List<Bank> banks = bankRepository.findAllByStatusOrderByShortNameAsc(EStatus.ACTIVE.name());
        return bankMapper.toDTOList(banks);
    }
}

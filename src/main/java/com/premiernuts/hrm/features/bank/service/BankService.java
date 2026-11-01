package com.premiernuts.hrm.features.bank.service;

import com.premiernuts.hrm.features.bank.dto.BankDTO;

import java.util.List;

public interface BankService {

    List<BankDTO> getActiveBanks();
}

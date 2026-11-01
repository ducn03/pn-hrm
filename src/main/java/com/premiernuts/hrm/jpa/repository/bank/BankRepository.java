package com.premiernuts.hrm.jpa.repository.bank;

import com.premiernuts.hrm.jpa.domain.bank.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findByCode(String code);

    List<Bank> findAllByStatusOrderByShortNameAsc(String status);
}

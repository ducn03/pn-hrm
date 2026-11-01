package com.premiernuts.hrm.jpa.repository.finance;

import com.premiernuts.hrm.jpa.domain.finance.UserFinance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFinanceRepository extends JpaRepository<UserFinance, Long> {

    Optional<UserFinance> findByUserId(Long userId);

    boolean existsByUserId(Long userId);
}

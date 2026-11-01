package com.premiernuts.hrm.jpa.repository.contract;

import com.premiernuts.hrm.jpa.domain.contract.UserContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserContractRepository extends JpaRepository<UserContract, Long> {

    List<UserContract> findAllByUserIdOrderByStartDateDesc(Long userId);

    Optional<UserContract> findByContractNumber(String contractNumber);

    boolean existsByContractNumber(String contractNumber);
}

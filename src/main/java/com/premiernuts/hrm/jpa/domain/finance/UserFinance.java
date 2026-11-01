package com.premiernuts.hrm.jpa.domain.finance;

import com.premiernuts.hrm.jpa.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "T_USER_FINANCE")
@Getter
@Setter
public class UserFinance extends BaseEntity {

    @Column(name = "user_id", nullable = false, unique = true)
    private Long userId;

    @Column(name = "bank_id")
    private Long bankId;

    @Column(name = "bank_account_no", length = 50)
    private String bankAccountNo;

    @Column(name = "bank_branch", length = 100)
    private String bankBranch;

    @Column(name = "tax_code", length = 50)
    private String taxCode;

    @Column(name = "dependents_count", nullable = false)
    private Integer dependentsCount = 0;

    @Column(name = "social_insurance_no", length = 50)
    private String socialInsuranceNo;

    @Column(name = "health_insurance_no", length = 50)
    private String healthInsuranceNo;
}

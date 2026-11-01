package com.premiernuts.hrm.jpa.domain.contract;

import com.premiernuts.hrm.jpa.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "T_USER_CONTRACT")
@Getter
@Setter
public class UserContract extends BaseEntity {

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "contract_number", nullable = false, unique = true, length = 50)
    private String contractNumber;

    @Column(name = "contract_type", nullable = false, length = 30)
    private String contractType;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "signed_date")
    private LocalDate signedDate;

    @Column(name = "base_salary", nullable = false, precision = 15, scale = 2)
    private BigDecimal baseSalary = BigDecimal.ZERO;

    @Column(name = "allowance", nullable = false, precision = 15, scale = 2)
    private BigDecimal allowance = BigDecimal.ZERO;

    @Column(name = "note", length = 255)
    private String note;

    @Column(name = "attachment_url", length = 255)
    private String attachmentUrl;
}

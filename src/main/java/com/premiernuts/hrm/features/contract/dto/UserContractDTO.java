package com.premiernuts.hrm.features.contract.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class UserContractDTO {

    private Long id;
    private Long userId;
    private String contractNumber;
    private String contractType;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate signedDate;
    private BigDecimal baseSalary;
    private BigDecimal allowance;
    private String note;
    private String attachmentUrl;
    private String status;
    private Instant createdAt;
    private Instant updatedAt;
}

package com.premiernuts.hrm.features.finance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserFinanceDTO {

    private Long id;
    private Long userId;
    private Long bankId;
    private String bankCode;
    private String bankName;
    private String bankShortName;
    private String bankAccountNo;
    private String bankBranch;
    private String taxCode;
    private Integer dependentsCount;
    private String socialInsuranceNo;
    private String healthInsuranceNo;
    private String status;
}

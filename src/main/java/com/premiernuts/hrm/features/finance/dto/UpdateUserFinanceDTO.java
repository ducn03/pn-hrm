package com.premiernuts.hrm.features.finance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserFinanceDTO {

    private Long bankId;
    private String bankAccountNo;
    private String bankBranch;
    private String taxCode;
    private Integer dependentsCount;
    private String socialInsuranceNo;
    private String healthInsuranceNo;
}

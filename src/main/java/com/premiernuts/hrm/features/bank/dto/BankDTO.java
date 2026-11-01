package com.premiernuts.hrm.features.bank.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BankDTO {

    private Long id;
    private String code;
    private String name;
    private String shortName;
    private String bin;
    private String status;
}

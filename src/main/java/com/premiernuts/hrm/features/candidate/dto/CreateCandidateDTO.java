package com.premiernuts.hrm.features.candidate.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class CreateCandidateDTO {

    private Long companyId;
    private Long departmentId;
    private Long roleId;
    private String fullName;
    private String phone;
    private String email;
    private LocalDate dob;
    private String gender;
    private String citizenId;
    private String cvUrl;
    private String source;
    private Instant interviewDate;
    private Long interviewerId;
    private String interviewNote;
    private Integer interviewScore;
    private BigDecimal expectedSalary;
    private String stage;
}

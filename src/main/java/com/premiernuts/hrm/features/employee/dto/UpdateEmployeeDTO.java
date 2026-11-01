package com.premiernuts.hrm.features.employee.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateEmployeeDTO {

    private Long departmentId;
    private Long roleId;
    private String fullName;
    private String email;
    private String phone;
    private String avatarUrl;
    private String gender;
    private LocalDate dob;
    private String citizenId;
    private LocalDate citizenIssueDate;
    private String citizenIssuePlace;
    private String hometown;
    private String permanentAddress;
    private String currentAddress;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String status;
}

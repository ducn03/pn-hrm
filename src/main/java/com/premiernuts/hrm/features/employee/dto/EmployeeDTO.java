package com.premiernuts.hrm.features.employee.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeeDTO {

    private Long id;
    private Long companyId;
    private Long departmentId;
    private Long roleId;
    private String username;
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
    private Instant createdAt;
    private Instant updatedAt;
}

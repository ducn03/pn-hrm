package com.premiernuts.hrm.jpa.domain.user;

import com.premiernuts.hrm.jpa.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "T_USER")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "email", unique = true, length = 100)
    private String email;

    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "avatar_url", length = 255)
    private String avatarUrl;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "citizen_id", length = 20)
    private String citizenId;

    @Column(name = "citizen_issue_date")
    private LocalDate citizenIssueDate;

    @Column(name = "citizen_issue_place", length = 100)
    private String citizenIssuePlace;

    @Column(name = "hometown", length = 150)
    private String hometown;

    @Column(name = "permanent_address", length = 255)
    private String permanentAddress;

    @Column(name = "current_address", length = 255)
    private String currentAddress;

    @Column(name = "emergency_contact_name", length = 100)
    private String emergencyContactName;

    @Column(name = "emergency_contact_phone", length = 20)
    private String emergencyContactPhone;
}

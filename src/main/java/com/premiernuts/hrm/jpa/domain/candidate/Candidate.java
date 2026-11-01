package com.premiernuts.hrm.jpa.domain.candidate;

import com.premiernuts.hrm.jpa.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "T_CANDIDATE")
@Getter
@Setter
public class Candidate extends BaseEntity {

    @Column(name = "company_id", nullable = false)
    private Long companyId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "dob")
    private LocalDate dob;

    @Column(name = "gender", length = 10)
    private String gender;

    @Column(name = "citizen_id", length = 20)
    private String citizenId;

    @Column(name = "cv_url", length = 255)
    private String cvUrl;

    @Column(name = "source", length = 50)
    private String source = "DIRECT";

    @Column(name = "interview_date")
    private Instant interviewDate;

    @Column(name = "interviewer_id")
    private Long interviewerId;

    @Column(name = "interview_note", columnDefinition = "TEXT")
    private String interviewNote;

    @Column(name = "interview_score")
    private Integer interviewScore;

    @Column(name = "expected_salary", precision = 15, scale = 2)
    private BigDecimal expectedSalary;

    @Column(name = "stage", nullable = false, length = 30)
    private String stage = "APPLIED";

    @Column(name = "converted_user_id")
    private Long convertedUserId;
}

package com.premiernuts.hrm.jpa.domain.bank;

import com.premiernuts.hrm.jpa.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "M_BANK")
@Getter
@Setter
public class Bank extends BaseEntity {

    @Column(name = "code", nullable = false, unique = true, length = 50)
    private String code;

    @Column(name = "name", nullable = false, length = 150)
    private String name;

    @Column(name = "short_name", nullable = false, length = 50)
    private String shortName;

    @Column(name = "bin", length = 20)
    private String bin;
}

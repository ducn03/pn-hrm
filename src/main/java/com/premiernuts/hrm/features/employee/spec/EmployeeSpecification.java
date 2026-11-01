package com.premiernuts.hrm.features.employee.spec;

import com.premiernuts.hrm.core.enums.EStatus;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.employee.dto.EmployeeSearchDTO;
import com.premiernuts.hrm.jpa.domain.user.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class EmployeeSpecification {

    private EmployeeSpecification() {
    }

    public static Specification<User> filter(EmployeeSearchDTO searchDTO) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (searchDTO == null) {
                predicates.add(cb.notEqual(root.get("status"), EStatus.DELETED.name()));
                return cb.and(predicates.toArray(new Predicate[0]));
            }

            if (searchDTO.getCompanyId() != null) {
                predicates.add(cb.equal(root.get("companyId"), searchDTO.getCompanyId()));
            }

            if (searchDTO.getDepartmentId() != null) {
                predicates.add(cb.equal(root.get("departmentId"), searchDTO.getDepartmentId()));
            }

            if (StringHelper.isNotBlank(searchDTO.getStatus())) {
                predicates.add(cb.equal(root.get("status"), StringHelper.toUpperCase(searchDTO.getStatus())));
            } else {
                predicates.add(cb.notEqual(root.get("status"), EStatus.DELETED.name()));
            }

            if (StringHelper.isNotBlank(searchDTO.getKeyword())) {
                String pattern = StringHelper.toLikePattern(searchDTO.getKeyword());
                Predicate pUsername = cb.like(cb.lower(root.get("username")), pattern);
                Predicate pFullName = cb.like(cb.lower(root.get("fullName")), pattern);
                Predicate pEmail = cb.like(cb.lower(root.get("email")), pattern);
                Predicate pPhone = cb.like(cb.lower(root.get("phone")), pattern);
                Predicate pCitizenId = cb.like(cb.lower(root.get("citizenId")), pattern);
                predicates.add(cb.or(pUsername, pFullName, pEmail, pPhone, pCitizenId));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

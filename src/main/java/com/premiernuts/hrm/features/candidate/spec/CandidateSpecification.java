package com.premiernuts.hrm.features.candidate.spec;

import com.premiernuts.hrm.core.enums.EStatus;
import com.premiernuts.hrm.core.utils.StringHelper;
import com.premiernuts.hrm.features.candidate.dto.CandidateSearchDTO;
import com.premiernuts.hrm.jpa.domain.candidate.Candidate;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public final class CandidateSpecification {

    private CandidateSpecification() {
    }

    public static Specification<Candidate> filter(CandidateSearchDTO searchDTO) {
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

            if (StringHelper.isNotBlank(searchDTO.getStage())) {
                predicates.add(cb.equal(root.get("stage"), StringHelper.toUpperCase(searchDTO.getStage())));
            }

            if (StringHelper.isNotBlank(searchDTO.getStatus())) {
                predicates.add(cb.equal(root.get("status"), StringHelper.toUpperCase(searchDTO.getStatus())));
            } else {
                predicates.add(cb.notEqual(root.get("status"), EStatus.DELETED.name()));
            }

            if (StringHelper.isNotBlank(searchDTO.getKeyword())) {
                String pattern = StringHelper.toLikePattern(searchDTO.getKeyword());
                Predicate pFullName = cb.like(cb.lower(root.get("fullName")), pattern);
                Predicate pPhone = cb.like(cb.lower(root.get("phone")), pattern);
                Predicate pEmail = cb.like(cb.lower(root.get("email")), pattern);
                predicates.add(cb.or(pFullName, pPhone, pEmail));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}

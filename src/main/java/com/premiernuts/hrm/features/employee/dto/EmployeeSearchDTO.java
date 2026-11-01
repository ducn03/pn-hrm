package com.premiernuts.hrm.features.employee.dto;

import com.premiernuts.hrm.core.constants.CommonConstants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeSearchDTO {

    private String keyword;
    private Long departmentId;
    private Long companyId;
    private String status;
    private Integer page = 1;
    private Integer size = 10;

    public int getNormalizedPage() {
        if (this.page == null || this.page < 1) {
            return 0;
        }
        return this.page - 1;
    }

    public int getNormalizedSize() {
        if (this.size == null || this.size < 1) {
            return 10;
        }
        return Math.min(CommonConstants.MAX_PAGE_SIZE, this.size);
    }
}

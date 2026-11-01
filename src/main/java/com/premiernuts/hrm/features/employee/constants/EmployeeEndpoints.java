package com.premiernuts.hrm.features.employee.constants;

import com.premiernuts.hrm.core.constants.ApiConstants;

public final class EmployeeEndpoints {

    private EmployeeEndpoints() {
    }

    public static final String BASE = ApiConstants.API_V1 + "/hrm/employees";
    public static final String ID = "/{id}";
}

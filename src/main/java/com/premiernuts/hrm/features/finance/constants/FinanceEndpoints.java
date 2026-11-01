package com.premiernuts.hrm.features.finance.constants;

import com.premiernuts.hrm.core.constants.ApiConstants;

public final class FinanceEndpoints {

    private FinanceEndpoints() {
    }

    public static final String BASE = ApiConstants.API_V1 + "/hrm/finance";
    public static final String BY_USER_ID = "/users/{userId}";
}

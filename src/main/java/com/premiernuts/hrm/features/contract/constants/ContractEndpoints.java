package com.premiernuts.hrm.features.contract.constants;

import com.premiernuts.hrm.core.constants.ApiConstants;

public final class ContractEndpoints {

    private ContractEndpoints() {
    }

    public static final String BASE = ApiConstants.API_V1 + "/hrm/contracts";
    public static final String ID = "/{id}";
    public static final String BY_USER = "/users/{userId}";
}

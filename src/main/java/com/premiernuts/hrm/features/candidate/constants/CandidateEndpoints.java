package com.premiernuts.hrm.features.candidate.constants;

import com.premiernuts.hrm.core.constants.ApiConstants;

public final class CandidateEndpoints {

    private CandidateEndpoints() {
    }

    public static final String BASE = ApiConstants.API_V1 + "/hrm/candidates";
    public static final String ID = "/{id}";
    public static final String STAGE = "/{id}/stage";
    public static final String CONVERT = "/{id}/convert";
}

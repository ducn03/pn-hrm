package com.premiernuts.hrm.core.exception;

public interface ErrorCodes {

    interface COMMON {
        String BAD_REQUEST = "40000001";
        String INTERNAL_SERVER_ERROR = "50000001";
        String NOT_FOUND = "40400001";
        String LOG_ACTIVITY_ERROR = "40000002";
    }

    interface UNAUTHORIZED {
        String TOKEN_EXPIRED = "40100001";
        String UNAUTHORIZED = "40100002";
        String TOKEN_INVALID = "40100003";
    }

    interface FORBIDDEN {
        String ACCESS_DENIED = "40300001";
        String ROLE_NOT_ALLOWED = "40300002";
    }

    interface VALIDATION {
        String INVALID_INPUT = "40002001";
        String FULL_NAME_REQUIRED = "40002004";
        String EMAIL_INVALID = "40002007";
        String STATUS_INVALID = "40002009";
    }

    interface FINANCE {
        String FINANCE_NOT_FOUND = "40010001";
        String INVALID_ACCOUNT_NO = "40010002";
        String BANK_NOT_FOUND = "40010003";
        String DEPENDENTS_COUNT_NEGATIVE = "40010004";
        String USER_NOT_FOUND = "40010005";
        String USER_ID_REQUIRED = "40010006";
    }

    interface CONTRACT {
        String CONTRACT_NOT_FOUND = "40011001";
        String CONTRACT_NUMBER_EXISTS = "40011002";
        String INVALID_DATE_RANGE = "40011003";
        String INVALID_CONTRACT_TYPE = "40011004";
        String USER_ID_REQUIRED = "40011005";
        String CONTRACT_NUMBER_REQUIRED = "40011006";
        String START_DATE_REQUIRED = "40011007";
        String SALARY_NEGATIVE = "40011008";
        String ALLOWANCE_NEGATIVE = "40011009";
        String USER_NOT_FOUND = "40011010";
    }

    interface CANDIDATE {
        String CANDIDATE_NOT_FOUND = "40012001";
        String ALREADY_CONVERTED = "40012002";
        String INVALID_PHONE = "40012003";
        String INVALID_STAGE = "40012004";
        String COMPANY_ID_REQUIRED = "40012005";
        String CONVERTED_USER_ID_REQUIRED = "40012006";
        String USER_NOT_FOUND = "40012007";
    }

    interface EMPLOYEE {
        String EMPLOYEE_NOT_FOUND = "40013001";
        String FULL_NAME_REQUIRED = "40013002";
        String PHONE_INVALID = "40013003";
        String EMAIL_INVALID = "40013004";
        String CITIZEN_ID_INVALID = "40013005";
        String EMAIL_EXISTS = "40013006";
    }
}

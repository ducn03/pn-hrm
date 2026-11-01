package com.premiernuts.hrm.core.constants;

public final class CommonConstants {

    private CommonConstants() {
    }

    public static final String DEFAULT_PAGE_INDEX = "1";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final int MAX_PAGE_SIZE = 100;
    public static final Long DEFAULT_COMPANY_ID = 1L;

    public static final String HEADER_ACCEPT_LANGUAGE = "Accept-Language";
    public static final String PARAM_LANG = "lang";
    public static final String DEFAULT_LANGUAGE = "vi";

    public static final String STATUS_ACTIVE = "ACTIVE";
    public static final String STATUS_INACTIVE = "INACTIVE";
    public static final String STATUS_DELETED = "DELETED";
    public static final String STATUS_LOCKED = "LOCKED";
    public static final String STATUS_EXPIRED = "EXPIRED";

    // HRM Success Message Codes
    public static final String MSG_FINANCE_UPDATED = "20010001";
    public static final String MSG_CONTRACT_CREATED = "20011001";
    public static final String MSG_CONTRACT_UPDATED = "20011002";
    public static final String MSG_CANDIDATE_CREATED = "20012001";
    public static final String MSG_CANDIDATE_UPDATED = "20012002";
    public static final String MSG_CANDIDATE_CONVERTED = "20012003";
}

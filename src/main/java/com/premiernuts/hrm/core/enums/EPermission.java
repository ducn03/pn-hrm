package com.premiernuts.hrm.core.enums;

import java.util.Set;

public enum EPermission {
    // PhÃ¢n há»‡ Quáº£n Trá»‹ NhÃ¢n Sá»± (IDENTITY)
    USER_VIEW,
    USER_MANAGE,
    ROLE_ASSIGN,

    // PhÃ¢n há»‡ Kho Váº­n & Váº­t TÆ° (INVENTORY)
    INVENTORY_VIEW,
    INVENTORY_IMPORT,
    INVENTORY_EXPORT,
    INVENTORY_AUDIT,

    // PhÃ¢n há»‡ Quáº£n LÃ½ Chi NhÃ¡nh (BRANCH)
    BRANCH_MANAGE,
    BRANCH_INVENTORY_VIEW,

    // PhÃ¢n há»‡ TÃ i ChÃ­nh Káº¿ ToÃ¡n (FINANCE)
    FINANCE_VIEW,
    FINANCE_MANAGE,

    // PhÃ¢n há»‡ Sáº£n Xuáº¥t (MANUFACTURING)
    MANUFACTURING_VIEW,
    MANUFACTURING_EXECUTE,

    // PhÃ¢n há»‡ Kiá»ƒm Äá»‹nh Cháº¥t LÆ°á»£ng (QC)
    QC_VIEW,
    QC_INSPECT,

    // PhÃ¢n há»‡ Ban GiÃ¡m Äá»‘c (MANAGEMENT)
    DIRECTOR_APPROVE,
    REPORT_VIEW,

    // PhÃ¢n há»‡ Há»‡ Thá»‘ng Äáº·c Quyá»n (SYSTEM)
    DASHBOARD_VIEW,
    LOG_VIEW,
    HEALTH_VIEW,

    // Phân hệ Quản Lý Doanh Nghiệp (COMPANY)
    COMPANY_VIEW,
    COMPANY_MANAGE,

    // Phân hệ Quản Trị Nhân Sự (HRM)
    HRM_VIEW,
    HRM_MANAGE;

    private static final Set<String> SYSTEM_PERMISSIONS = Set.of(
            Names.DASHBOARD_VIEW,
            Names.LOG_VIEW,
            Names.HEALTH_VIEW
    );

    public static boolean isSystemPermission(String code) {
        if (code == null) {
            return false;
        }
        return SYSTEM_PERMISSIONS.contains(code.toUpperCase());
    }

    public static final class Names {

        private Names() {
        }

        public static final String USER_VIEW = "USER_VIEW";
        public static final String USER_MANAGE = "USER_MANAGE";
        public static final String ROLE_ASSIGN = "ROLE_ASSIGN";

        public static final String INVENTORY_VIEW = "INVENTORY_VIEW";
        public static final String INVENTORY_IMPORT = "INVENTORY_IMPORT";
        public static final String INVENTORY_EXPORT = "INVENTORY_EXPORT";
        public static final String INVENTORY_AUDIT = "INVENTORY_AUDIT";

        public static final String BRANCH_MANAGE = "BRANCH_MANAGE";
        public static final String BRANCH_INVENTORY_VIEW = "BRANCH_INVENTORY_VIEW";

        public static final String FINANCE_VIEW = "FINANCE_VIEW";
        public static final String FINANCE_MANAGE = "FINANCE_MANAGE";

        public static final String MANUFACTURING_VIEW = "MANUFACTURING_VIEW";
        public static final String MANUFACTURING_EXECUTE = "MANUFACTURING_EXECUTE";

        public static final String QC_VIEW = "QC_VIEW";
        public static final String QC_INSPECT = "QC_INSPECT";

        public static final String DIRECTOR_APPROVE = "DIRECTOR_APPROVE";
        public static final String REPORT_VIEW = "REPORT_VIEW";

        public static final String DASHBOARD_VIEW = "DASHBOARD_VIEW";
        public static final String LOG_VIEW = "LOG_VIEW";
        public static final String HEALTH_VIEW = "HEALTH_VIEW";

        public static final String COMPANY_VIEW = "COMPANY_VIEW";
        public static final String COMPANY_MANAGE = "COMPANY_MANAGE";

        public static final String HRM_VIEW = "HRM_VIEW";
        public static final String HRM_MANAGE = "HRM_MANAGE";
    }
}

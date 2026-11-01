-- ==============================================================================
-- PREMIER NUTS ERP - HRM DATABASE SCHEMA (schema-hrm)
-- Engine   : PostgreSQL 18
-- Database : premiernutserp
-- Domain   : Human Resource Management (HRM)
-- ==============================================================================

-- ------------------------------------------------------------------------------
-- 1. BẢNG MASTER: DANH MỤC NGÂN HÀNG (VIETQR / NAPAS)
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS M_BANK (
    id BIGSERIAL PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(150) NOT NULL,
    short_name VARCHAR(50) NOT NULL,
    bin VARCHAR(20),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------------------------
-- 2. BẢNG TRANSACTION: THÔNG TIN HỒ SƠ NHÂN VIÊN
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS T_USER (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT NOT NULL,
    department_id BIGINT,
    role_id BIGINT,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    avatar_url VARCHAR(255),
    gender VARCHAR(10),
    dob DATE,
    citizen_id VARCHAR(20),
    citizen_issue_date DATE,
    citizen_issue_place VARCHAR(100),
    hometown VARCHAR(150),
    permanent_address VARCHAR(255),
    current_address VARCHAR(255),
    emergency_contact_name VARCHAR(100),
    emergency_contact_phone VARCHAR(20),
    last_login_at TIMESTAMP WITH TIME ZONE,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------------------------
-- 3. BẢNG TRANSACTION: THÔNG TIN TÀI CHÍNH, THUẾ & TÀI KHOẢN NGÂN HÀNG
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS T_USER_FINANCE (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE,
    bank_id BIGINT,
    bank_account_no VARCHAR(50),
    bank_branch VARCHAR(100),
    tax_code VARCHAR(50),
    dependents_count INT NOT NULL DEFAULT 0,
    social_insurance_no VARCHAR(50),
    health_insurance_no VARCHAR(50),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------------------------
-- 4. BẢNG TRANSACTION: HỢP ĐỒNG LAO ĐỘNG NHÂN SỰ
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS T_USER_CONTRACT (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    contract_number VARCHAR(50) NOT NULL UNIQUE,
    contract_type VARCHAR(30) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    signed_date DATE,
    base_salary NUMERIC(15, 2) NOT NULL DEFAULT 0,
    allowance NUMERIC(15, 2) NOT NULL DEFAULT 0,
    note VARCHAR(255),
    attachment_url VARCHAR(255),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------------------------
-- 5. BẢNG TRANSACTION: ỨNG VIÊN & TUYỂN DỤNG NHÂN SỰ (ATS)
-- ------------------------------------------------------------------------------
CREATE TABLE IF NOT EXISTS T_CANDIDATE (
    id BIGSERIAL PRIMARY KEY,
    company_id BIGINT NOT NULL,
    department_id BIGINT,
    role_id BIGINT,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    dob DATE,
    gender VARCHAR(10),
    citizen_id VARCHAR(20),
    cv_url VARCHAR(255),
    source VARCHAR(50) DEFAULT 'DIRECT',
    interview_date TIMESTAMP WITH TIME ZONE,
    interviewer_id BIGINT,
    interview_note TEXT,
    interview_score INT,
    expected_salary NUMERIC(15, 2),
    stage VARCHAR(30) NOT NULL DEFAULT 'APPLIED',
    converted_user_id BIGINT,
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- ------------------------------------------------------------------------------
-- SEED DATA: DANH MỤC NGÂN HÀNG PHỔ BIẾN
-- ------------------------------------------------------------------------------
INSERT INTO M_BANK (code, name, short_name, bin, status)
VALUES 
    ('VCB', 'Ngân hàng TMCP Ngoại thương Việt Nam', 'Vietcombank', '970436', 'ACTIVE'),
    ('MB', 'Ngân hàng TMCP Quân đội', 'MBBank', '970422', 'ACTIVE'),
    ('TCB', 'Ngân hàng TMCP Kỹ thương Việt Nam', 'Techcombank', '970407', 'ACTIVE'),
    ('BIDV', 'Ngân hàng TMCP Đầu tư và Phát triển Việt Nam', 'BIDV', '970418', 'ACTIVE'),
    ('CTG', 'Ngân hàng TMCP Công thương Việt Nam', 'VietinBank', '970415', 'ACTIVE'),
    ('ACB', 'Ngân hàng TMCP Á Châu', 'ACB', '970416', 'ACTIVE'),
    ('VPB', 'Ngân hàng TMCP Việt Nam Thịnh Vượng', 'VPBank', '970432', 'ACTIVE'),
    ('VBA', 'Ngân hàng Nông nghiệp và Phát triển Nông thôn Việt Nam', 'Agribank', '970405', 'ACTIVE'),
    ('TPB', 'Ngân hàng TMCP Tiên Phong', 'TPBank', '970423', 'ACTIVE'),
    ('STB', 'Ngân hàng TMCP Sài Gòn Thương Tín', 'Sacombank', '970403', 'ACTIVE')
ON CONFLICT (code) DO NOTHING;

package com.spring.airag.common.constants;

/**
 * 安全相关常量类
 */
public class SecurityConstants {

    // JWT相关
    public static final String JWT_HEADER = "Authorization";
    public static final String JWT_PREFIX = "Bearer ";
    public static final String JWT_SECRET = "mySecretKeyForAIRAGDemo";
    public static final long JWT_EXPIRATION = 86400L; // 24小时（秒）
    public static final String JWT_ISSUER = "airag-system";
    public static final String JWT_AUDIENCE = "airag-users";

    // 加密相关
    public static final String ENCRYPT_ALGORITHM_MD5 = "MD5";
    public static final String ENCRYPT_ALGORITHM_SHA256 = "SHA-256";
    public static final String ENCRYPT_ALGORITHM_SHA512 = "SHA-512";
    public static final String ENCRYPT_ALGORITHM_AES = "AES";
    public static final String ENCRYPT_ALGORITHM_RSA = "RSA";

    // 密码相关
    public static final int PASSWORD_MIN_LENGTH = 6;
    public static final int PASSWORD_MAX_LENGTH = 20;
    public static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d@$!%*#?&]{6,}$"; // 至少包含一个字母和一个数字

    // 权限相关
    public static final String PERMISSION_READ = "READ";
    public static final String PERMISSION_WRITE = "WRITE";
    public static final String PERMISSION_DELETE = "DELETE";
    public static final String PERMISSION_EXECUTE = "EXECUTE";
    public static final String PERMISSION_ADMIN = "ADMIN";

    // 角色相关
    public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_SUPER_ADMIN = "ROLE_SUPER_ADMIN";
    public static final String ROLE_GUEST = "ROLE_GUEST";

    // 认证相关
    public static final String AUTHENTICATION_PROVIDER = "authenticationProvider";
    public static final String AUTHORIZATION_PROVIDER = "authorizationProvider";
    public static final String AUTHENTICATION_SUCCESS = "AUTHENTICATION_SUCCESS";
    public static final String AUTHENTICATION_FAILURE = "AUTHENTICATION_FAILURE";

    // 安全配置相关
    public static final String SECURITY_CONFIG_IGNORED_PATHS = "/static/**,/public/**,/css/**,/js/**,/images/**,/webjars/**";
    public static final String SECURITY_CONFIG_PERMIT_ALL_PATHS = "/login,/register,/auth/**,/api/public/**";
    public static final String SECURITY_CONFIG_AUTHENTICATED_PATHS = "/api/private/**,/admin/**,/user/**";

    // 会话相关
    public static final String SESSION_ID = "sessionId";
    public static final String SESSION_TIMEOUT = "sessionTimeout";
    public static final int SESSION_DEFAULT_TIMEOUT = 1800; // 30分钟（秒）
    public static final int SESSION_MAX_INACTIVE_INTERVAL = 3600; // 1小时（秒）

    // 安全头相关
    public static final String HEADER_XSS_PROTECTION = "X-XSS-Protection";
    public static final String HEADER_STRICT_TRANSPORT_SECURITY = "Strict-Transport-Security";
    public static final String HEADER_CONTENT_TYPE_OPTIONS = "X-Content-Type-Options";
    public static final String HEADER_FRAME_OPTIONS = "X-Frame-Options";
    public static final String HEADER_CSP = "Content-Security-Policy";

    // CSRF相关
    public static final String CSRF_COOKIE_NAME = "XSRF-TOKEN";
    public static final String CSRF_HEADER_NAME = "X-XSRF-TOKEN";
    public static final String CSRF_PARAMETER_NAME = "_csrf";

    // 认证类型
    public static final String AUTH_TYPE_JWT = "JWT";
    public static final String AUTH_TYPE_SESSION = "SESSION";
    public static final String AUTH_TYPE_OAUTH2 = "OAUTH2";
    public static final String AUTH_TYPE_BASIC = "BASIC";
    public static final String AUTH_TYPE_DIGEST = "DIGEST";

    // 加密盐值相关
    public static final int SALT_LENGTH = 16;
    public static final String SALT_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    // 安全事件
    public static final String EVENT_LOGIN_SUCCESS = "LOGIN_SUCCESS";
    public static final String EVENT_LOGIN_FAILURE = "LOGIN_FAILURE";
    public static final String EVENT_LOGOUT_SUCCESS = "LOGOUT_SUCCESS";
    public static final String EVENT_ACCESS_DENIED = "ACCESS_DENIED";
    public static final String EVENT_SECURITY_VIOLATION = "SECURITY_VIOLATION";

    // 安全级别
    public static final String SECURITY_LEVEL_LOW = "LOW";
    public static final String SECURITY_LEVEL_MEDIUM = "MEDIUM";
    public static final String SECURITY_LEVEL_HIGH = "HIGH";
    public static final String SECURITY_LEVEL_VERY_HIGH = "VERY_HIGH";

    // 安全策略
    public static final String SECURITY_POLICY_STRICT = "STRICT";
    public static final String SECURITY_POLICY_MODERATE = "MODERATE";
    public static final String SECURITY_POLICY_RELAXED = "RELAXED";

    // 证书相关
    public static final String CERTIFICATE_TYPE_PEM = "PEM";
    public static final String CERTIFICATE_TYPE_P12 = "PKCS12";
    public static final String CERTIFICATE_TYPE_JKS = "JKS";
    public static final String CERTIFICATE_ALIAS = "certificate_alias";

    // 安全扫描相关
    public static final String SECURITY_SCAN_ENABLED = "security.scan.enabled";
    public static final String SECURITY_SCAN_INTERVAL = "security.scan.interval";
    public static final String SECURITY_SCAN_REPORT_PATH = "security.scan.report.path";

    // 敏感信息相关
    public static final String SENSITIVE_FIELD_PASSWORD = "password";
    public static final String SENSITIVE_FIELD_PHONE = "phone";
    public static final String SENSITIVE_FIELD_ID_CARD = "idCard";
    public static final String SENSITIVE_FIELD_BANK_CARD = "bankCard";
    public static final String SENSITIVE_FIELD_EMAIL = "email";
    public static final String SENSITIVE_FIELD_ADDRESS = "address";

    // 安全算法相关
    public static final String ALGORITHM_RSA_2048 = "RSA2048";
    public static final String ALGORITHM_AES_256 = "AES256";
    public static final String ALGORITHM_SHA_256_WITH_RSA = "SHA256withRSA";
    public static final String ALGORITHM_HMAC_SHA256 = "HmacSHA256";
}
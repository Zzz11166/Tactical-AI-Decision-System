package com.spring.airag.common.constants;

/**
 * API相关常量类
 */
public class ApiConstants {

    // API版本
    public static final String API_VERSION_V1 = "/api/v1";
    public static final String API_VERSION_V2 = "/api/v2";

    // API前缀
    public static final String API_PREFIX = "/api";
    public static final String ADMIN_API_PREFIX = "/admin/api";
    public static final String OPEN_API_PREFIX = "/open/api";

    // 认证相关
    public static final String AUTH_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String TOKEN_REFRESH_PATH = "/auth/refresh";

    // 通用API路径
    public static final String HEALTH_CHECK_PATH = "/health";
    public static final String METRICS_PATH = "/metrics";
    public static final String INFO_PATH = "/info";
    public static final String ACTUATOR_PATH = "/actuator";

    // 用户相关
    public static final String USER_LOGIN_PATH = "/login";
    public static final String USER_LOGOUT_PATH = "/logout";
    public static final String USER_REGISTER_PATH = "/register";
    public static final String USER_PROFILE_PATH = "/profile";

    // 数据相关
    public static final String DATA_QUERY_PATH = "/query";
    public static final String DATA_SAVE_PATH = "/save";
    public static final String DATA_UPDATE_PATH = "/update";
    public static final String DATA_DELETE_PATH = "/delete";
    public static final String DATA_BATCH_PATH = "/batch";

    // 分页相关
    public static final String PAGE_PARAM = "page";
    public static final String SIZE_PARAM = "size";
    public static final String SORT_PARAM = "sort";
    public static final String ORDER_PARAM = "order";

    // 搜索相关
    public static final String SEARCH_PARAM = "keyword";
    public static final String FILTER_PARAM = "filter";
    public static final String QUERY_PARAM = "q";

    // 上传下载相关
    public static final String UPLOAD_PATH = "/upload";
    public static final String DOWNLOAD_PATH = "/download";
    public static final String FILE_PARAM = "file";

    // 权限相关
    public static final String PERMISSION_READ = "READ";
    public static final String PERMISSION_WRITE = "WRITE";
    public static final String PERMISSION_DELETE = "DELETE";
    public static final String PERMISSION_ADMIN = "ADMIN";

    // 响应头相关
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_CONTENT_LENGTH = "Content-Length";
    public static final String HEADER_CACHE_CONTROL = "Cache-Control";
    public static final String HEADER_EXPIRES = "Expires";
    public static final String HEADER_PRAGMA = "Pragma";

    // 常用Content-Type
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CONTENT_TYPE_FORM = "application/x-www-form-urlencoded";
    public static final String CONTENT_TYPE_MULTIPART = "multipart/form-data";
    public static final String CONTENT_TYPE_TEXT = "text/plain";
    public static final String CONTENT_TYPE_XML = "application/xml";

    // 通用错误码
    public static final Integer ERROR_CODE_SUCCESS = 200;
    public static final Integer ERROR_CODE_ERROR = 500;
    public static final Integer ERROR_CODE_INVALID_PARAM = 400;
    public static final Integer ERROR_CODE_UNAUTHORIZED = 401;
    public static final Integer ERROR_CODE_FORBIDDEN = 403;
    public static final Integer ERROR_CODE_NOT_FOUND = 404;
    public static final Integer ERROR_CODE_METHOD_NOT_ALLOWED = 405;
    public static final Integer ERROR_CODE_REQUEST_TIMEOUT = 408;
    public static final Integer ERROR_CODE_TOO_MANY_REQUESTS = 429;
}
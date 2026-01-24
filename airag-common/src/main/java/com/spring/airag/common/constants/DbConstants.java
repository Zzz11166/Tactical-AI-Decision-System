package com.spring.airag.common.constants;

/**
 * 数据库相关常量类
 */
public class DbConstants {

    // 数据库类型
    public static final String DB_TYPE_MYSQL = "mysql";
    public static final String DB_TYPE_POSTGRESQL = "postgresql";
    public static final String DB_TYPE_ORACLE = "oracle";
    public static final String DB_TYPE_SQLSERVER = "sqlserver";
    public static final String DB_TYPE_SQLITE = "sqlite";

    // 数据库连接相关
    public static final String DB_DRIVER_MYSQL = "com.mysql.cj.jdbc.Driver";
    public static final String DB_DRIVER_POSTGRESQL = "org.postgresql.Driver";
    public static final String DB_DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    public static final String DB_DRIVER_SQLSERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

    // 连接池相关
    public static final String CONNECTION_POOL_HIKARI = "hikari";
    public static final String CONNECTION_POOL_DRUID = "druid";
    public static final String CONNECTION_POOL_C3P0 = "c3p0";

    // 分页相关
    public static final String PAGE_HELPER_MYSQL = "mysql";
    public static final String PAGE_HELPER_POSTGRESQL = "postgresql";
    public static final String PAGE_HELPER_ORACLE = "oracle";
    public static final String PAGE_HELPER_SQLSERVER = "sqlserver";

    // 默认分页参数
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 1000;
    public static final int MIN_PAGE_SIZE = 1;

    // 数据库字段相关
    public static final String FIELD_ID = "id";
    public static final String FIELD_CREATE_TIME = "create_time";
    public static final String FIELD_UPDATE_TIME = "update_time";
    public static final String FIELD_CREATED_BY = "created_by";
    public static final String FIELD_UPDATED_BY = "updated_by";
    public static final String FIELD_DELETED = "deleted";
    public static final String FIELD_VERSION = "version";
    public static final String FIELD_STATUS = "status";

    // 通用状态值
    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_INACTIVE = 0;
    public static final Integer STATUS_DELETED = -1;
    public static final Integer STATUS_PENDING = 2;
    public static final Integer STATUS_APPROVED = 3;
    public static final Integer STATUS_REJECTED = 4;

    // 排序相关
    public static final String ORDER_ASC = "ASC";
    public static final String ORDER_DESC = "DESC";
    public static final String ORDER_BY = "ORDER BY ";

    // 事务相关
    public static final int TRANSACTION_ISOLATION_DEFAULT = -1;
    public static final int TRANSACTION_ISOLATION_READ_UNCOMMITTED = 1;
    public static final int TRANSACTION_ISOLATION_READ_COMMITTED = 2;
    public static final int TRANSACTION_ISOLATION_REPEATABLE_READ = 4;
    public static final int TRANSACTION_ISOLATION_SERIALIZABLE = 8;

    // 索引相关
    public static final String INDEX_UNIQUE = "UNIQUE";
    public static final String INDEX_NORMAL = "NORMAL";
    public static final String INDEX_PRIMARY = "PRIMARY";

    // 数据库约束
    public static final String CONSTRAINT_FOREIGN_KEY = "FOREIGN KEY";
    public static final String CONSTRAINT_UNIQUE = "UNIQUE";
    public static final String CONSTRAINT_PRIMARY_KEY = "PRIMARY KEY";
    public static final String CONSTRAINT_CHECK = "CHECK";
    public static final String CONSTRAINT_NOT_NULL = "NOT NULL";
    public static final String CONSTRAINT_DEFAULT = "DEFAULT";

    // SQL关键字
    public static final String SQL_SELECT = "SELECT";
    public static final String SQL_INSERT = "INSERT";
    public static final String SQL_UPDATE = "UPDATE";
    public static final String SQL_DELETE = "DELETE";
    public static final String SQL_WHERE = "WHERE";
    public static final String SQL_FROM = "FROM";
    public static final String SQL_JOIN = "JOIN";
    public static final String SQL_LEFT_JOIN = "LEFT JOIN";
    public static final String SQL_RIGHT_JOIN = "RIGHT JOIN";
    public static final String SQL_INNER_JOIN = "INNER JOIN";
    public static final String SQL_ON = "ON";
    public static final String SQL_AND = "AND";
    public static final String SQL_OR = "OR";
    public static final String SQL_LIKE = "LIKE";
    public static final String SQL_BETWEEN = "BETWEEN";
    public static final String SQL_IN = "IN";
    public static final String SQL_EXISTS = "EXISTS";

    // 数据库连接池配置相关
    public static final int DEFAULT_MIN_IDLE = 5;
    public static final int DEFAULT_MAX_POOL_SIZE = 20;
    public static final int DEFAULT_CONNECTION_TIMEOUT = 30000;
    public static final int DEFAULT_IDLE_TIMEOUT = 600000;
    public static final int DEFAULT_MAX_LIFETIME = 1800000;
    public static final int DEFAULT_LEAK_DETECTION_THRESHOLD = 60000;

    // 数据库方言
    public static final String DIALECT_MYSQL = "MySQL8Dialect";
    public static final String DIALECT_POSTGRESQL = "PostgreSQLDialect";
    public static final String DIALECT_ORACLE = "Oracle12cDialect";
    public static final String DIALECT_SQLSERVER = "SQLServerDialect";

    // 数据库表命名规范
    public static final String TABLE_PREFIX_T = "t_";
    public static final String TABLE_PREFIX_SYS = "sys_";
    public static final String TABLE_PREFIX_BIZ = "biz_";
    public static final String TABLE_PREFIX_LOG = "log_";

    // 数据库审计字段
    public static final String AUDIT_FIELD_CREATED_TIME = "created_time";
    public static final String AUDIT_FIELD_CREATED_BY = "created_by";
    public static final String AUDIT_FIELD_UPDATED_TIME = "updated_time";
    public static final String AUDIT_FIELD_UPDATED_BY = "updated_by";
    public static final String AUDIT_FIELD_DELETED_FLAG = "deleted_flag";

    // 数据库索引命名规范
    public static final String INDEX_PREFIX_IDX = "idx_";
    public static final String INDEX_PREFIX_UK = "uk_"; // 唯一索引
    public static final String INDEX_PREFIX_PK = "pk_"; // 主键

    // 数据库触发器命名规范
    public static final String TRIGGER_PREFIX = "trg_";

    // 数据库视图命名规范
    public static final String VIEW_PREFIX = "v_";

    // 数据库存储过程命名规范
    public static final String PROCEDURE_PREFIX = "proc_";

    // 数据库函数命名规范
    public static final String FUNCTION_PREFIX = "func_";

    // 数据库连接超时相关
    public static final int CONNECTION_TIMEOUT_SHORT = 5000; // 5秒
    public static final int CONNECTION_TIMEOUT_MEDIUM = 10000; // 10秒
    public static final int CONNECTION_TIMEOUT_LONG = 30000; // 30秒

    // 数据库查询超时相关
    public static final int QUERY_TIMEOUT_SHORT = 5000; // 5秒
    public static final int QUERY_TIMEOUT_MEDIUM = 15000; // 15秒
    public static final int QUERY_TIMEOUT_LONG = 30000; // 30秒
    public static final int QUERY_TIMEOUT_VERY_LONG = 60000; // 60秒

    // 数据库批量操作相关
    public static final int BATCH_SIZE_SMALL = 10;
    public static final int BATCH_SIZE_MEDIUM = 100;
    public static final int BATCH_SIZE_LARGE = 1000;
    public static final int BATCH_SIZE_X_LARGE = 10000;

    // 数据库锁相关
    public static final String LOCK_MODE_SHARED = "SHARED";
    public static final String LOCK_MODE_EXCLUSIVE = "EXCLUSIVE";
    public static final String LOCK_MODE_OPTIMISTIC = "OPTIMISTIC";
    public static final String LOCK_MODE_PESSIMISTIC = "PESSIMISTIC";

    // 数据库事务传播行为
    public static final String TX_PROPAGATION_REQUIRED = "REQUIRED";
    public static final String TX_PROPAGATION_SUPPORTS = "SUPPORTS";
    public static final String TX_PROPAGATION_MANDATORY = "MANDATORY";
    public static final String TX_PROPAGATION_REQUIRES_NEW = "REQUIRES_NEW";
    public static final String TX_PROPAGATION_NOT_SUPPORTED = "NOT_SUPPORTED";
    public static final String TX_PROPAGATION_NEVER = "NEVER";
    public static final String TX_PROPAGATION_NESTED = "NESTED";
}
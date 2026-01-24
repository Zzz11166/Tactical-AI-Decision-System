-- Auth微服务数据库表结构
-- 数据库类型: PostgreSQL

-- 在airag_auth数据库中创建表
-- 用户表
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    role VARCHAR(20) DEFAULT 'USER',
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 用户会话表
CREATE TABLE IF NOT EXISTS user_sessions (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    session_token VARCHAR(255) UNIQUE NOT NULL,
    expires_at TIMESTAMP NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 角色权限表
CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(50) UNIQUE NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建索引以提高查询性能
CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);

-- 创建更新时间触发器函数
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- 为需要自动更新updated_at字段的表创建触发器
CREATE TRIGGER update_users_updated_at 
    BEFORE UPDATE ON users 
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- 插入初始数据
INSERT INTO roles (role_name, description, created_at) VALUES 
('ADMIN', '系统管理员角色', CURRENT_TIMESTAMP),
('USER', '普通用户角色', CURRENT_TIMESTAMP),
('DIRECTOR', '导演角色', CURRENT_TIMESTAMP)
ON CONFLICT (role_name) DO NOTHING;

INSERT INTO users (username, password_hash, email, role, status) VALUES 
('admin', '$2a$10$8K1TKD7eTyW/R1QHA4z3HOp9FZR.a8yaYXGvV7P.X.qJ5.tZpX9QO', 'admin@airag.com', 'ADMIN', 'ACTIVE'),
('director', '$2a$10$8K1TKD7eTyW/R1QHA4z3HOp9FZR.a8yaYXGvV7P.X.qJ5.tZpX9QO', 'director@airag.com', 'DIRECTOR', 'ACTIVE')
ON CONFLICT (username) DO NOTHING;
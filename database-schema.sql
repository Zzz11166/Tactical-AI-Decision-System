-- AI辅助决策系统数据库建表脚本
-- 数据库类型: PostgreSQL

-- 为各个微服务创建数据库
-- 注意：这些CREATE DATABASE语句需要以超级用户身份执行
/*
CREATE DATABASE airag_auth WITH OWNER = postgres ENCODING = 'UTF8';
CREATE DATABASE airag_data WITH OWNER = postgres ENCODING = 'UTF8';
CREATE DATABASE airag_scene WITH OWNER = postgres ENCODING = 'UTF8';
CREATE DATABASE airag_decision WITH OWNER = postgres ENCODING = 'UTF8';
*/

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

-- 在airag_data数据库中创建表
-- 演练场景表
CREATE TABLE IF NOT EXISTS exercise_scenes (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL, -- 演练类型：进攻作战、防御作战等
    description TEXT,
    creator_id INTEGER REFERENCES users(id),
    start_time TIMESTAMP,
    end_time TIMESTAMP,
    status VARCHAR(20) DEFAULT 'PREPARING', -- PREPARING, RUNNING, FINISHED, PAUSED
    config JSONB, -- 场景配置信息
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 模型/单位表
CREATE TABLE IF NOT EXISTS models (
    id VARCHAR(50) PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    name VARCHAR(255) NOT NULL,
    side VARCHAR(10) NOT NULL, -- RED, BLUE, NEUTRAL
    type VARCHAR(20) NOT NULL, -- INFANTRY, TANK, AIRCRAFT, SHIP, ARTILLERY
    position_x DOUBLE PRECISION DEFAULT 0.0,
    position_y DOUBLE PRECISION DEFAULT 0.0,
    position_z DOUBLE PRECISION DEFAULT 0.0,
    status VARCHAR(20) DEFAULT 'ACTIVE', -- ACTIVE, MOVING, ATTACKING, DETECTING, DAMAGED, DESTROYED
    detection_status VARCHAR(20) DEFAULT 'UNDETECTED', -- UNDETECTED, DETECTED, HIDDEN
    detected_by VARCHAR(50),
    detection_time TIMESTAMP,
    health DOUBLE PRECISION DEFAULT 100.0,
    attack_power DOUBLE PRECISION DEFAULT 10.0,
    defense_power DOUBLE PRECISION DEFAULT 5.0,
    attack_range DOUBLE PRECISION DEFAULT 100.0,
    detection_range DOUBLE PRECISION DEFAULT 500.0,
    move_speed DOUBLE PRECISION DEFAULT 10.0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 文件信息表
CREATE TABLE IF NOT EXISTS files (
    id SERIAL PRIMARY KEY,
    filename VARCHAR(255) NOT NULL,
    original_name VARCHAR(255) NOT NULL,
    file_size BIGINT,
    content_type VARCHAR(100),
    bucket_name VARCHAR(100),
    object_name VARCHAR(255),
    file_category VARCHAR(50), -- SCENARIO, TACTICAL_CASE, MAP, VIDEO等
    uploaded_by INTEGER REFERENCES users(id),
    uploaded_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 战术案例表
CREATE TABLE IF NOT EXISTS tactical_cases (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    scenario_type VARCHAR(50), -- 案例适用的场景类型
    forces_composition JSONB, -- 双方兵力配置
    tactics_used TEXT, -- 使用的战术
    outcome TEXT, -- 案例结果
    lessons_learned TEXT, -- 经验教训
    difficulty_level INTEGER DEFAULT 1, -- 难度等级 1-5
    tags TEXT[], -- 标签数组
    created_by INTEGER REFERENCES users(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- RAG知识库表
CREATE TABLE IF NOT EXISTS rag_knowledge (
    id VARCHAR(50) PRIMARY KEY,
    parent_id VARCHAR(50), -- 父文档ID，用于跟踪原始文档
    title VARCHAR(500) NOT NULL,
    content TEXT NOT NULL,
    category VARCHAR(50), -- KNOWLEDGE, TACTICAL_CASE, RULE, PROCEDURE等
    embedding_vector JSONB, -- 向量嵌入，使用JSONB存储，推荐生产环境使用pgvector扩展
    metadata JSONB, -- 额外元数据
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 在airag_scene数据库中创建表
-- 场景模板表
CREATE TABLE IF NOT EXISTS scene_templates (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    type VARCHAR(50) NOT NULL,
    description TEXT,
    template_config JSONB, -- 场景模板配置
    created_by INTEGER REFERENCES users(id),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 地形数据表
CREATE TABLE IF NOT EXISTS terrain_data (
    id SERIAL PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    name VARCHAR(255) NOT NULL,
    terrain_type VARCHAR(50), -- MOUNTAIN, PLAIN, URBAN, WATER等
    elevation_data JSONB, -- 高程数据
    feature_data JSONB, -- 地形特征数据
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 在airag_decision数据库中创建表
-- 决策建议表
CREATE TABLE IF NOT EXISTS decision_suggestions (
    id SERIAL PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    side VARCHAR(10) NOT NULL,
    situation_summary TEXT,
    actions JSONB, -- 决策行动数组
    threats JSONB, -- 威胁评估数组
    reasoning TEXT,
    confidence DOUBLE PRECISION DEFAULT 0.0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    generated_by VARCHAR(50) -- 生成方式：AI, MANUAL, HYBRID
);

-- 决策行动表
CREATE TABLE IF NOT EXISTS decision_actions (
    id SERIAL PRIMARY KEY,
    suggestion_id INTEGER REFERENCES decision_suggestions(id),
    action_type VARCHAR(50) NOT NULL, -- ATTACK, DEFEND, MOVE, RECONNAISSANCE, OCCUPY, PATROL等
    model_id VARCHAR(50) REFERENCES models(id),
    target_model_id VARCHAR(50),
    target_position_x DOUBLE PRECISION,
    target_position_y DOUBLE PRECISION,
    target_position_z DOUBLE PRECISION,
    description TEXT,
    priority INTEGER DEFAULT 2, -- 1-高优先级, 2-中优先级, 3-低优先级
    confidence DOUBLE PRECISION DEFAULT 0.0,
    parameters JSONB, -- 额外参数
    executed BOOLEAN DEFAULT FALSE,
    executed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 威胁评估表
CREATE TABLE IF NOT EXISTS threat_assessments (
    id SERIAL PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    source_model_id VARCHAR(50) REFERENCES models(id), -- 威胁来源
    target_model_id VARCHAR(50) REFERENCES models(id), -- 威胁目标
    threat_level DOUBLE PRECISION DEFAULT 0.0, -- 威胁等级 0.0-1.0
    threat_type VARCHAR(50), -- IMMEDIATE_THREAT, POTENTIAL_THREAT, MONITORING
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 决策日志表
CREATE TABLE IF NOT EXISTS decision_logs (
    id SERIAL PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    side VARCHAR(10) NOT NULL,
    decision_content JSONB, -- 完整的决策内容
    decision_type VARCHAR(20) DEFAULT 'AI', -- AI, MANUAL, HYBRID
    confidence DOUBLE PRECISION,
    execution_result VARCHAR(20), -- SUCCESS, PARTIAL_SUCCESS, FAILED
    feedback_score INTEGER, -- 反馈评分 1-5
    feedback_comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    executed_at TIMESTAMP
);

-- 指令表
CREATE TABLE IF NOT EXISTS commands (
    id SERIAL PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    sender_id VARCHAR(50), -- 发送者ID（用户或AI）
    receiver_id VARCHAR(50), -- 接收者ID（模型或用户组）
    command_type VARCHAR(50) NOT NULL, -- MOVE, ATTACK, DEFEND, STOP等
    parameters JSONB, -- 指令参数
    status VARCHAR(20) DEFAULT 'PENDING', -- PENDING, EXECUTED, FAILED, CANCELLED
    executed_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 状态变更日志表
CREATE TABLE IF NOT EXISTS state_logs (
    id SERIAL PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    model_id VARCHAR(50) REFERENCES models(id),
    event_type VARCHAR(50) NOT NULL, -- POSITION_UPDATE, STATUS_CHANGE, HEALTH_CHANGE, DAMAGE等
    old_value JSONB,
    new_value JSONB,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 消息表（用于WebSocket通信）
CREATE TABLE IF NOT EXISTS messages (
    id SERIAL PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    message_type VARCHAR(50) NOT NULL, -- MODEL_STATE, COMMAND, DECISION, CHAT等
    sender VARCHAR(100), -- 发送者
    receiver VARCHAR(100), -- 接收者
    content JSONB NOT NULL,
    broadcast BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 参与者表（场景参与者）
CREATE TABLE IF NOT EXISTS participants (
    id SERIAL PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    user_id INTEGER REFERENCES users(id),
    side VARCHAR(10), -- RED, BLUE, DIRECTOR
    role VARCHAR(50), -- PLAYER, REFEREE, OBSERVER
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 创建索引以提高查询性能
CREATE INDEX IF NOT EXISTS idx_users_username ON users(username);
CREATE INDEX IF NOT EXISTS idx_models_scene_id ON models(scene_id);
CREATE INDEX IF NOT EXISTS idx_models_side ON models(side);
CREATE INDEX IF NOT EXISTS idx_models_type ON models(type);
CREATE INDEX IF NOT EXISTS idx_models_position ON models USING GIST (POINT(position_x, position_y));
CREATE INDEX IF NOT EXISTS idx_decision_suggestions_scene_id ON decision_suggestions(scene_id);
CREATE INDEX IF NOT EXISTS idx_decision_suggestions_side ON decision_suggestions(side);
CREATE INDEX IF NOT EXISTS idx_commands_scene_id ON commands(scene_id);
CREATE INDEX IF NOT EXISTS idx_state_logs_scene_model ON state_logs(scene_id, model_id);
CREATE INDEX IF NOT EXISTS idx_threat_assessments_scene ON threat_assessments(scene_id);
CREATE INDEX IF NOT EXISTS idx_exercise_scenes_status ON exercise_scenes(status);
CREATE INDEX IF NOT EXISTS idx_rag_knowledge_category ON rag_knowledge(category);
CREATE INDEX IF NOT EXISTS idx_decision_logs_created_at ON decision_logs(created_at);
CREATE INDEX IF NOT EXISTS idx_messages_scene_type ON messages(scene_id, message_type);

-- 创建更新时间触发器函数
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- 为需要自动更新updated_at字段的表创建触发器
CREATE TRIGGER update_exercise_scenes_updated_at 
    BEFORE UPDATE ON exercise_scenes 
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_models_updated_at 
    BEFORE UPDATE ON models 
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_rag_knowledge_updated_at 
    BEFORE UPDATE ON rag_knowledge 
    FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();

-- 插入初始数据
-- 用户角色枚举
INSERT INTO users (username, password_hash, email, role, status) VALUES 
('admin', '$2a$10$8K1TKD7eTyW/R1QHA4z3HOp9FZR.a8yaYXGvV7P.X.qJ5.tZpX9QO', 'admin@airag.com', 'ADMIN', 'ACTIVE'),
('director', '$2a$10$8K1TKD7eTyW/R1QHA4z3HOp9FZR.a8yaYXGvV7P.X.qJ5.tZpX9QO', 'director@airag.com', 'DIRECTOR', 'ACTIVE')
ON CONFLICT (username) DO NOTHING;

-- 默认演练场景类型
INSERT INTO exercise_scenes (id, name, type, description, status) VALUES
('SCENE_DEFAULT', '默认演练场景', 'COMBAT_TRAINING', '系统默认创建的演练场景', 'PREPARING')
ON CONFLICT (id) DO NOTHING;

-- 提示：在使用向量搜索前，需要安装pgvector扩展
-- CREATE EXTENSION IF NOT EXISTS vector;
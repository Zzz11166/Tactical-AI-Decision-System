-- AI辅助决策系统 - 数据管理服务数据库表结构

-- 1. 文件信息表
CREATE TABLE IF NOT EXISTS files (
    id VARCHAR(50) PRIMARY KEY,
    original_name VARCHAR(255) NOT NULL COMMENT '原始文件名',
    file_name VARCHAR(255) NOT NULL COMMENT '存储文件名',
    file_path VARCHAR(500) NOT NULL COMMENT '文件路径',
    file_size BIGINT COMMENT '文件大小(字节)',
    file_type VARCHAR(100) COMMENT '文件类型(MIME)',
    file_extension VARCHAR(20) COMMENT '文件扩展名',
    storage_type VARCHAR(50) DEFAULT 'MINIO' COMMENT '存储类型：MINIO, LOCAL, S3等',
    upload_user_id VARCHAR(50) COMMENT '上传用户ID',
    upload_user_name VARCHAR(100) COMMENT '上传用户名',
    business_type VARCHAR(50) COMMENT '业务类型',
    business_id VARCHAR(50) COMMENT '业务ID',
    status INTEGER DEFAULT 1 COMMENT '状态：1-启用，0-禁用',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. 演练场景表
CREATE TABLE IF NOT EXISTS exercise_scenes (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL COMMENT '场景名称',
    type VARCHAR(50) NOT NULL COMMENT '演练类型：进攻作战、防御作战等',
    description TEXT COMMENT '场景描述',
    map_id VARCHAR(100) COMMENT '地图ID',
    creator_id VARCHAR(50) COMMENT '创建者ID',
    start_time TIMESTAMP COMMENT '开始时间',
    end_time TIMESTAMP COMMENT '结束时间',
    status VARCHAR(20) DEFAULT 'PREPARING' COMMENT '状态：PREPARING, RUNNING, FINISHED, PAUSED',
    config JSONB COMMENT '场景配置信息',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 3. 模型/单位表
CREATE TABLE IF NOT EXISTS models (
    id VARCHAR(50) PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    name VARCHAR(255) NOT NULL COMMENT '模型名称',
    side VARCHAR(10) NOT NULL COMMENT '阵营：RED, BLUE, NEUTRAL',
    type VARCHAR(20) NOT NULL COMMENT '类型：INFANTRY, TANK, AIRCRAFT, SHIP, ARTILLERY',
    position_x DOUBLE PRECISION DEFAULT 0.0 COMMENT 'X坐标',
    position_y DOUBLE PRECISION DEFAULT 0.0 COMMENT 'Y坐标',
    position_z DOUBLE PRECISION DEFAULT 0.0 COMMENT 'Z坐标',
    status VARCHAR(20) DEFAULT 'ACTIVE' COMMENT '状态：ACTIVE, MOVING, ATTACKING, DETECTING, DAMAGED, DESTROYED',
    detection_status VARCHAR(20) DEFAULT 'UNDETECTED' COMMENT '检测状态：UNDETECTED, DETECTED, HIDDEN',
    detected_by VARCHAR(50) COMMENT '被谁发现',
    detection_time TIMESTAMP COMMENT '发现时间',
    health DOUBLE PRECISION DEFAULT 100.0 COMMENT '生命值',
    attack_power DOUBLE PRECISION DEFAULT 10.0 COMMENT '攻击力',
    defense_power DOUBLE PRECISION DEFAULT 5.0 COMMENT '防御力',
    attack_range DOUBLE PRECISION DEFAULT 100.0 COMMENT '攻击范围',
    detection_range DOUBLE PRECISION DEFAULT 500.0 COMMENT '探测范围',
    move_speed DOUBLE PRECISION DEFAULT 10.0 COMMENT '移动速度',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 4. 战术案例表
CREATE TABLE IF NOT EXISTS tactical_cases (
    id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(255) NOT NULL COMMENT '案例标题',
    description TEXT COMMENT '案例描述',
    scenario_type VARCHAR(50) COMMENT '案例适用的场景类型',
    forces_composition JSONB COMMENT '双方兵力配置',
    tactics_used TEXT COMMENT '使用的战术',
    outcome TEXT COMMENT '案例结果',
    lessons_learned TEXT COMMENT '经验教训',
    difficulty_level INTEGER DEFAULT 1 COMMENT '难度等级 1-5',
    tags TEXT[] COMMENT '标签数组',
    created_by VARCHAR(50) COMMENT '创建者',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 5. RAG知识库表
CREATE TABLE IF NOT EXISTS rag_knowledge (
    id VARCHAR(50) PRIMARY KEY,
    parent_id VARCHAR(50) COMMENT '父文档ID，用于跟踪原始文档',
    title VARCHAR(500) NOT NULL COMMENT '标题',
    content TEXT NOT NULL COMMENT '内容',
    category VARCHAR(50) COMMENT '类别：KNOWLEDGE, TACTICAL_CASE, RULE, PROCEDURE等',
    embedding_vector JSONB COMMENT '向量嵌入，使用JSONB存储',
    metadata JSONB COMMENT '额外元数据',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 6. 威胁评估表
CREATE TABLE IF NOT EXISTS threat_assessments (
    id VARCHAR(50) PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    source_model_id VARCHAR(50) REFERENCES models(id) COMMENT '威胁来源',
    target_model_id VARCHAR(50) REFERENCES models(id) COMMENT '威胁目标',
    threat_level DOUBLE PRECISION DEFAULT 0.0 COMMENT '威胁等级 0.0-1.0',
    threat_type VARCHAR(50) COMMENT '威胁类型：IMMEDIATE_THREAT, POTENTIAL_THREAT, MONITORING',
    description TEXT COMMENT '描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 7. 决策日志表
CREATE TABLE IF NOT EXISTS decision_logs (
    id VARCHAR(50) PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    side VARCHAR(10) NOT NULL COMMENT '阵营',
    decision_content JSONB COMMENT '完整的决策内容',
    decision_type VARCHAR(20) DEFAULT 'AI' COMMENT '类型：AI, MANUAL, HYBRID',
    confidence DOUBLE PRECISION COMMENT '置信度',
    execution_result VARCHAR(20) COMMENT '执行结果：SUCCESS, PARTIAL_SUCCESS, FAILED',
    feedback_score INTEGER COMMENT '反馈评分 1-5',
    feedback_comment TEXT COMMENT '反馈评论',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    executed_at TIMESTAMP COMMENT '执行时间'
);

-- 8. 指令表
CREATE TABLE IF NOT EXISTS commands (
    id VARCHAR(50) PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    sender_id VARCHAR(50) COMMENT '发送者ID（用户或AI）',
    receiver_id VARCHAR(50) COMMENT '接收者ID（模型或用户组）',
    command_type VARCHAR(50) NOT NULL COMMENT '指令类型：MOVE, ATTACK, DEFEND, STOP等',
    parameters JSONB COMMENT '指令参数',
    status VARCHAR(20) DEFAULT 'PENDING' COMMENT '状态：PENDING, EXECUTED, FAILED, CANCELLED',
    executed_at TIMESTAMP COMMENT '执行时间',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 9. 状态变更日志表
CREATE TABLE IF NOT EXISTS state_logs (
    id VARCHAR(50) PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    model_id VARCHAR(50) REFERENCES models(id),
    event_type VARCHAR(50) NOT NULL COMMENT '事件类型：POSITION_UPDATE, STATUS_CHANGE, HEALTH_CHANGE, DAMAGE等',
    old_value JSONB COMMENT '旧值',
    new_value JSONB COMMENT '新值',
    description TEXT COMMENT '描述',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 10. 消息表（用于WebSocket通信）
CREATE TABLE IF NOT EXISTS messages (
    id VARCHAR(50) PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    message_type VARCHAR(50) NOT NULL COMMENT '消息类型：MODEL_STATE, COMMAND, DECISION, CHAT等',
    sender VARCHAR(100) COMMENT '发送者',
    receiver VARCHAR(100) COMMENT '接收者',
    content JSONB NOT NULL COMMENT '消息内容',
    broadcast BOOLEAN DEFAULT FALSE COMMENT '是否广播',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 11. 参与者表（场景参与者）
CREATE TABLE IF NOT EXISTS participants (
    id VARCHAR(50) PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    user_id VARCHAR(50) COMMENT '用户ID',
    side VARCHAR(10) COMMENT '阵营：RED, BLUE, DIRECTOR',
    role VARCHAR(50) COMMENT '角色：PLAYER, REFEREE, OBSERVER',
    joined_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 12. 场景模板表
CREATE TABLE IF NOT EXISTS scene_templates (
    id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL COMMENT '模板名称',
    type VARCHAR(50) NOT NULL COMMENT '模板类型',
    description TEXT COMMENT '描述',
    template_config JSONB COMMENT '场景模板配置',
    created_by VARCHAR(50) COMMENT '创建者',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 13. 地形数据表
CREATE TABLE IF NOT EXISTS terrain_data (
    id VARCHAR(50) PRIMARY KEY,
    scene_id VARCHAR(50) REFERENCES exercise_scenes(id),
    name VARCHAR(255) NOT NULL COMMENT '地形名称',
    terrain_type VARCHAR(50) COMMENT '地形类型：MOUNTAIN, PLAIN, URBAN, WATER等',
    elevation_data JSONB COMMENT '高程数据',
    feature_data JSONB COMMENT '地形特征数据',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 14. 本地消息表（用于分布式事务一致性）
CREATE TABLE IF NOT EXISTS local_message_queue (
    id VARCHAR(50) PRIMARY KEY,
    message_key VARCHAR(255) NOT NULL COMMENT '消息键',
    message_body TEXT NOT NULL COMMENT '消息体',
    message_type VARCHAR(50) NOT NULL COMMENT '消息类型',
    destination_service VARCHAR(100) COMMENT '目标服务',
    status VARCHAR(20) DEFAULT 'PENDING' COMMENT '状态：PENDING, SENT, FAILED',
    retry_count INTEGER DEFAULT 0 COMMENT '重试次数',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed_at TIMESTAMP COMMENT '处理时间'
);

-- 创建索引以提高查询性能
CREATE INDEX idx_files_created_at ON files(created_at);
CREATE INDEX idx_files_storage_type ON files(storage_type);
CREATE INDEX idx_files_business_type ON files(business_type);

CREATE INDEX idx_exercise_scenes_created_at ON exercise_scenes(created_at);
CREATE INDEX idx_exercise_scenes_status ON exercise_scenes(status);
CREATE INDEX idx_exercise_scenes_type ON exercise_scenes(type);

CREATE INDEX idx_models_scene_id ON models(scene_id);
CREATE INDEX idx_models_side ON models(side);
CREATE INDEX idx_models_type ON models(type);
CREATE INDEX idx_models_status ON models(status);

CREATE INDEX idx_tactical_cases_type ON tactical_cases(scenario_type);
CREATE INDEX idx_tactical_cases_difficulty ON tactical_cases(difficulty_level);

CREATE INDEX idx_rag_knowledge_category ON rag_knowledge(category);
CREATE INDEX idx_rag_knowledge_created_at ON rag_knowledge(created_at);

CREATE INDEX idx_threat_assessments_scene ON threat_assessments(scene_id);
CREATE INDEX idx_threat_assessments_source ON threat_assessments(source_model_id);

CREATE INDEX idx_decision_logs_scene_side ON decision_logs(scene_id, side);
CREATE INDEX idx_decision_logs_created_at ON decision_logs(created_at);

CREATE INDEX idx_commands_scene_status ON commands(scene_id, status);
CREATE INDEX idx_commands_created_at ON commands(created_at);

CREATE INDEX idx_state_logs_scene_model ON state_logs(scene_id, model_id);
CREATE INDEX idx_state_logs_event_type ON state_logs(event_type);

CREATE INDEX idx_messages_scene_type ON messages(scene_id, message_type);
CREATE INDEX idx_messages_created_at ON messages(created_at);

CREATE INDEX idx_participants_scene_user ON participants(scene_id, user_id);

CREATE INDEX idx_local_message_status ON local_message_queue(status);
CREATE INDEX idx_local_message_destination ON local_message_queue(destination_service);

-- 创建更新时间触发器函数（用于自动更新updated_at字段）
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ language 'plpgsql';

-- 为需要自动更新updated_at字段的表创建触发器
DO $$ 
DECLARE
    table_name TEXT;
BEGIN
    FOR table_name IN 
        SELECT tablename 
        FROM pg_tables 
        WHERE schemaname = 'public' 
        AND tablename IN ('files', 'exercise_scenes', 'tactical_cases', 'rag_knowledge', 
                          'decision_logs', 'scene_templates', 'terrain_data')
    LOOP
        EXECUTE 'DROP TRIGGER IF EXISTS update_' || table_name || '_updated_at ON ' || table_name;
        EXECUTE 'CREATE TRIGGER update_' || table_name || '_updated_at 
                 BEFORE UPDATE ON ' || table_name || ' 
                 FOR EACH ROW EXECUTE FUNCTION update_updated_at_column();';
    END LOOP;
END $$;
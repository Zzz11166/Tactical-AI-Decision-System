# AI辅助决策系统

## 项目概述

本项目是一个基于RAG（检索增强生成）的AI辅助决策系统，主要用于军事演练场景。系统分为四个主要模块：AI辅助决策、作战场景构设、态势实时展示和演练数据管理。

## 技术栈

- **后端框架**: Spring Boot 3.0.2
- **数据库**: PostgreSQL
- **缓存**: Redis
- **搜索引擎**: Elasticsearch 8.8.0
- **对象存储**: MinIO 8.5.4
- **实时通信**: WebSocket
- **任务调度**: XXL-JOB 2.4.0
- **向量模型**: Sentence-BERT

## 系统架构

### 1. AI辅助决策模块

- **RAGService**: 基于Elasticsearch的检索增强生成服务
- **AIDecisionService**: AI辅助决策服务，生成决策建议
- **DecisionSuggestion**: 决策建议数据模型

### 2. 作战场景构设模块

- **SceneBuilderService**: 场景构建服务，支持AI辅助构建
- **SceneBuildRequest**: 场景构建请求数据模型

### 3. 态势实时展示模块

- **SituationService**: 态势展示服务，处理模型状态和指令
- **Model**: 模型数据模型
- **ExerciseScene**: 演练场景数据模型
- **ModelCommand**: 模型指令数据模型
- **WebSocketController**: WebSocket消息控制器

### 4. 演练数据管理模块

- **DataManagementService**: 数据管理服务，处理文件存储和同步
- **Annotation**: 标绘数据模型

## 核心功能

### 1. RAG知识库构建

- 支持文本分块和向量化
- 基于Elasticsearch的向量检索
- 混合检索（关键词+向量）

### 2. 实时态势展示

- 基于WebSocket的实时数据推送
- 模型状态同步和指令处理
- 损伤计算和侦查成功判定

### 3. AI辅助决策

- 基于当前态势和战术案例生成决策建议
- 支持决策建议的自动执行
- 决策日志记录和回放

### 4. 数据管理

- 基于MinIO的文件存储
- 大文件异步处理
- 数据同步到ES

## 关键问题解决方案

### 1. 实时性和数据传输量优化

- 采用WebSocket实现全双工通信
- 批量处理状态更新
- 只传输变化的数据
- 使用Redis缓存热点数据

### 2. 损伤计算

```java
private double calculateDamage(Model attacker, Model target) {
    // 基础伤害
    double damage = baseDamage;

    // 根据攻击者和目标类型调整伤害
    damage *= getDamageMultiplier(attacker.getType(), target.getType());

    // 添加随机因素
    double randomFactor = 1.0 + (ThreadLocalRandom.current().nextDouble() * 2 - 1) * damageRandomFactor;
    damage *= randomFactor;

    // 根据距离调整伤害
    double distance = calculateDistance(attacker.getPosition(), target.getPosition());
    double distanceFactor = Math.max(0.1, 1.0 - distance / attacker.getAttackRange());
    damage *= distanceFactor;

    return damage;
}
```

### 3. 侦查成功判定

```java
private double calculateDetectionProbability(Model detector, Model target, double distance) {
    // 基础概率
    double probability = baseDetectionProbability;

    // 根据距离调整
    double distanceFactor = Math.max(0.1, 1.0 - distance / detector.getDetectionRange());
    probability *= distanceFactor;

    // 根据地形调整
    probability *= (1.0 - terrainFactor);

    // 根据天气调整
    probability *= (1.0 - weatherFactor);

    // 根据目标类型调整
    probability *= getDetectionTypeFactor(target.getType());

    return Math.max(0.0, Math.min(1.0, probability));
}
```

### 4. 非结构化数据存储和检索

- 图片和表格文件存储在MinIO中
- 文件元数据索引到Elasticsearch
- 支持基于文件内容的检索

### 5. 知识库数据同步

```java
public void syncModelDataToES(List<Model> models) {
    try {
        for (Model model : models) {
            // 将模型数据转换为JSON
            String modelJson = convertModelToJson(model);

            // 添加到RAG知识库
            ragService.addKnowledge(
                    model.getId(), 
                    model.getName(), 
                    modelJson, 
                    "MODEL"
            );
        }
    } catch (Exception e) {
        log.error("同步模型数据到ES失败", e);
        throw new RuntimeException("同步模型数据到ES失败", e);
    }
}
```

### 6. Sentence-BERT向量生成

```java
public float[] generateEmbedding(String text) {
    try {
        // 使用Sentence-BERT模型生成向量
        INDArray input = Nd4j.create(new float[][]{preprocessText(text)});
        INDArray output = sentenceBertModel.output(input);

        // 归一化向量
        float[] vector = output.toFloatVector();
        float norm = 0.0f;
        for (float v : vector) {
            norm += v * v;
        }
        norm = (float) Math.sqrt(norm);
        for (int i = 0; i < vectorDimension; i++) {
            vector[i] = vector[i] / norm;
        }

        return vector;
    } catch (Exception e) {
        log.error("生成文本向量失败: {}", text, e);
        throw new RuntimeException("生成文本向量失败", e);
    }
}
```

### 7. WebSocket消息协议设计

- **状态同步**: 定期推送模型状态变化
- **指令广播**: 将用户指令广播给所有相关用户
- **断线重连**: 客户端自动重连并恢复状态

### 8. 多客户端状态一致性

- 使用乐观锁机制
- 指令按时间戳排序
- 服务器端权威验证

### 9. 大文件处理策略

- 分片处理
- 失败重试
- 资源限制

### 10. MinIO断点续传

- 使用MinIO的分片上传API
- 记录已上传的分片信息
- 支持断点后继续上传

### 11. Elasticsearch索引设计

```json
{
  "mappings": {
    "properties": {
      "id": {"type": "keyword"},
      "title": {
        "type": "text",
        "analyzer": "ik_max_word"
      },
      "content": {
        "type": "text",
        "analyzer": "ik_max_word"
      },
      "category": {"type": "keyword"},
      "location": {"type": "geo_point"},
      "force": {"type": "keyword"},
      "unit": {"type": "keyword"},
      "embedding": {
        "type": "dense_vector",
        "dims": 768
      }
    }
  }
}
```

### 12. ES与MinIO数据一致性

- 本地消息表记录待同步数据
- XXL-JOB定时任务处理同步
- 失败重试机制
- 幂等性设计

## 快速开始

1. 安装依赖环境：
   - PostgreSQL
   - Redis
   - Elasticsearch
   - MinIO
   - XXL-JOB

2. 配置application.yml文件，修改数据库连接等信息

3. 启动应用：
   ```bash
   mvn spring-boot:run
   ```

4. 访问API文档：http://localhost:8080/swagger-ui.html

## 项目结构

```
src/main/java/com/spring/airagdemo/
├── config/              # 配置类
│   ├── ElasticsearchConfig.java
│   ├── MinioConfig.java
│   └── WebSocketConfig.java
├── data/                # 数据管理模块
│   ├── Annotation.java
│   └── DataManagementService.java
├── decision/            # AI辅助决策模块
│   ├── AIDecisionService.java
│   └── DecisionSuggestion.java
├── rag/                 # RAG知识库模块
│   └── RAGService.java
├── scene/               # 场景构设模块
│   ├── SceneBuildRequest.java
│   └── SceneBuilderService.java
├── situation/           # 态势展示模块
│   ├── ExerciseScene.java
│   ├── Model.java
│   ├── ModelCommand.java
│   ├── Position.java
│   └── SituationService.java
├── websocket/           # WebSocket控制器
│   └── WebSocketController.java
└── AiragDemoApplication.java  # 主应用类
```

## 扩展开发

如需扩展功能，请遵循以下原则：

1. 遵循现有的包结构和命名规范
2. 添加适当的日志记录
3. 编写单元测试
4. 更新API文档

## 许可证

本项目采用MIT许可证。

package com.spring.airag.common.constants;

/**
 * 业务常量类
 */
public class BusinessConstants {

    // 模型类型常量
    public static class ModelType {
        public static final String INFANTRY = "INFANTRY";    // 步兵
        public static final String TANK = "TANK";            // 坦克
        public static final String AIRCRAFT = "AIRCRAFT";    // 飞机
        public static final String SHIP = "SHIP";            // 舰船
        public static final String ARTILLERY = "ARTILLERY";  // 火炮
    }

    // 模型状态常量
    public static class ModelStatus {
        public static final String ACTIVE = "ACTIVE";        // 活跃
        public static final String MOVING = "MOVING";        // 移动中
        public static final String ATTACKING = "ATTACKING";  // 攻击中
        public static final String DETECTING = "DETECTING";  // 侦查中
        public static final String DAMAGED = "DAMAGED";      // 受损
        public static final String DESTROYED = "DESTROYED";  // 摧毁
    }

    // 指令类型常量
    public static class CommandType {
        public static final String MOVE = "MOVE";    // 移动
        public static final String ATTACK = "ATTACK";// 攻击
        public static final String DETECT = "DETECT";// 侦查
        public static final String STOP = "STOP";    // 停止
    }

    // 标绘类型常量
    public static class AnnotationType {
        public static final String POINT = "POINT";      // 点
        public static final String LINE = "LINE";        // 线
        public static final String POLYGON = "POLYGON";  // 多边形
        public static final String CIRCLE = "CIRCLE";    // 圆
        public static final String ICON = "ICON";        // 图标
        public static final String TEXT = "TEXT";        // 文本
    }

    // 军事力量标识
    public static class ForceSide {
        public static final String RED = "RED";    // 红方
        public static final String BLUE = "BLUE";  // 蓝方
    }

    // 场景状态常量
    public static class SceneStatus {
        public static final String CREATED = "CREATED";    // 已创建
        public static final String RUNNING = "RUNNING";    // 运行中
        public static final String PAUSED = "PAUSED";      // 已暂停
        public static final String COMPLETED = "COMPLETED";// 已完成
    }

    // 检测状态常量
    public static class DetectionStatus {
        public static final String UNDETECTED = "UNDETECTED"; // 未被发现
        public static final String DETECTED = "DETECTED";     // 已被发现
        public static final String HIDDEN = "HIDDEN";         // 隐藏
    }

    // 作战类型常量
    public static class OperationType {
        public static final String OFFENSIVE = "OFFENSIVE";   // 进攻作战
        public static final String DEFENSIVE = "DEFENSIVE";   // 防御作战
        public static final String RECONNAISSANCE = "RECONNAISSANCE"; // 侦察作战
    }

    // 地形类型常量
    public static class TerrainType {
        public static final String PLAIN = "PLAIN";       // 平原
        public static final String MOUNTAIN = "MOUNTAIN"; // 山地
        public static final String URBAN = "URBAN";       // 城市
        public static final String FOREST = "FOREST";     // 丛林
    }

    // 天气类型常量
    public static class WeatherType {
        public static final String SUNNY = "SUNNY";  // 晴
        public static final String RAIN = "RAIN";    // 雨
        public static final String SNOW = "SNOW";    // 雪
    }

    // 优先级常量
    public static class Priority {
        public static final Integer HIGH = 1;    // 高优先级
        public static final Integer MEDIUM = 2;  // 中优先级
        public static final Integer LOW = 3;     // 低优先级
    }
}
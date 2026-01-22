package com.spring.airag.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 位置实体类
 */
@Data
public class Position implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double x;
    private Double y;
    private Double z;  // 高度，可选

    public Position() {
    }

    public Position(Double x, Double y) {
        this.x = x;
        this.y = y;
        this.z = 0.0;
    }

    public Position(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * 计算与另一个位置的距离
     */
    public double distanceTo(Position other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        double dz = (this.z != null && other.z != null) ? this.z - other.z : 0;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}

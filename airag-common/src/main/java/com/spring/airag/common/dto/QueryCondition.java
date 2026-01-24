package com.spring.airag.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 查询条件类
 */
@Data
public class QueryCondition implements Serializable {
    private static final long serialVersionUID = 1L;

    private Map<String, Object> conditions; // 查询条件
    private String sortField;               // 排序字段
    private String sortOrder;               // 排序方向
    private Integer page = 1;              // 页码
    private Integer size = 10;             // 每页大小

    public QueryCondition() {
        this.conditions = new HashMap<>();
    }

    /**
     * 添加查询条件
     */
    public QueryCondition addCondition(String key, Object value) {
        this.conditions.put(key, value);
        return this;
    }

    /**
     * 设置排序
     */
    public QueryCondition setSort(String field, String order) {
        this.sortField = field;
        this.sortOrder = order;
        return this;
    }

    /**
     * 设置分页
     */
    public QueryCondition setPage(int page, int size) {
        this.page = page;
        this.size = size;
        return this;
    }

    /**
     * 检查是否有排序条件
     */
    public boolean hasSort() {
        return sortField != null && !sortField.trim().isEmpty();
    }

    /**
     * 获取偏移量
     */
    public Integer getOffset() {
        return (page - 1) * size;
    }

    /**
     * 创建默认查询条件
     */
    public static QueryCondition of() {
        return new QueryCondition();
    }

    /**
     * 创建带条件的查询条件
     */
    public static QueryCondition of(Map<String, Object> conditions) {
        QueryCondition query = new QueryCondition();
        query.setConditions(conditions);
        return query;
    }
}
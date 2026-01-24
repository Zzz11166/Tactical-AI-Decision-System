package com.spring.airag.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 分页请求参数类
 */
@Data
public class RequestPage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer page = 1;      // 页码，默认为1
    private Integer size = 10;     // 每页大小，默认为10
    private String sortField;      // 排序字段
    private String sortOrder;      // 排序方向，ASC/DESC

    public RequestPage() {
    }

    public RequestPage(Integer page, Integer size) {
        this.page = page != null && page > 0 ? page : 1;
        this.size = size != null && size > 0 ? size : 10;
    }

    /**
     * 获取偏移量
     */
    public Integer getOffset() {
        return (page - 1) * size;
    }

    /**
     * 检查排序字段是否有效
     */
    public boolean hasSort() {
        return sortField != null && !sortField.trim().isEmpty();
    }
}
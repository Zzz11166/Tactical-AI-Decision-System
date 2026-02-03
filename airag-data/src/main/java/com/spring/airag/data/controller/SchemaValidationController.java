package com.spring.airag.data.controller;

import com.spring.airag.common.dto.Result;
import com.spring.airag.data.service.DatabaseValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 数据库表结构验证控制器
 */
@RestController
@RequestMapping("/data/schema")
public class SchemaValidationController {

    @Autowired
    private DatabaseValidationService databaseValidationService;

    /**
     * 验证数据库表结构
     */
    @GetMapping("/validate")
    public Result<Map<String, Object>> validateSchema() {
        Map<String, Object> result = databaseValidationService.validateDatabaseSchema();
        return Result.success("数据库表结构验证完成", result);
    }

    /**
     * 获取指定表的结构信息
     */
    @GetMapping("/structure/{tableName}")
    public Result<Map<String, Object>> getTableStructure(@PathVariable String tableName) {
        Map<String, Object> result = databaseValidationService.getTableStructure(tableName);
        return Result.success("获取表结构信息成功", result);
    }
}
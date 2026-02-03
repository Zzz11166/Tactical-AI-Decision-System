package com.spring.airag.data.test;

import com.spring.airag.data.service.DataManagementService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 数据服务测试
 */
@SpringBootTest
public class DataServiceTest {

    @org.springframework.beans.factory.annotation.Autowired
    private DataManagementService dataManagementService;

    @Test
    public void testDataManagementService() {
        assertNotNull(dataManagementService, "DataManagementService should not be null");
    }
}
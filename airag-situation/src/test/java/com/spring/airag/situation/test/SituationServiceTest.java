package com.spring.airag.situation.test;

import com.spring.airag.common.entity.Model;
import com.spring.airag.situation.service.SituationUpdateService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * 态势服务测试
 */
@SpringBootTest
public class SituationServiceTest {

    @Autowired
    private SituationUpdateService situationUpdateService;

    @Test
    public void testSituationUpdateService() {
        assertNotNull(situationUpdateService, "SituationUpdateService should not be null");
        
        // 创建测试模型数据
        Map<String, Model> models = new HashMap<>();
        Model model = new Model();
        model.setId("test-model-1");
        model.setName("测试模型1");
        model.setSide("RED");
        model.setType(Model.Type.TANK);
        models.put("test-model-1", model);
        
        // 测试推送功能（不会真正发送，但会验证方法调用）
        situationUpdateService.pushSituationUpdate("test-scene", models, "MODEL_UPDATE");
        
        // 测试连接数获取
        int connectionCount = situationUpdateService.getConnectionCount();
        // 连接数可能为0，因为我们还没有真正的WebSocket连接
    }
}
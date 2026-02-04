package com.spring.airag.data.mapper;

import com.spring.airag.data.entity.ModelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ModelMapper {
    
    /**
     * 插入模型信息
     */
    int insert(ModelEntity model);
    
    /**
     * 根据ID查询模型
     */
    ModelEntity selectById(@Param("id") String id);
    
    /**
     * 查询所有模型
     */
    List<ModelEntity> selectAll();
    
    /**
     * 根据场景ID查询模型
     */
    List<ModelEntity> selectBySceneId(@Param("sceneId") String sceneId);
    
    /**
     * 根据阵营查询模型
     */
    List<ModelEntity> selectBySide(@Param("side") String side);
    
    /**
     * 根据类型查询模型
     */
    List<ModelEntity> selectByType(@Param("type") String type);
    
    /**
     * 根据状态查询模型
     */
    List<ModelEntity> selectByStatus(@Param("status") String status);
    
    /**
     * 更新模型信息
     */
    int update(ModelEntity model);
    
    /**
     * 根据ID删除模型
     */
    int deleteById(@Param("id") String id);
}
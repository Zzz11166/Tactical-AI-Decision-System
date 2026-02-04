package com.spring.airag.data.mapper;

import com.spring.airag.data.entity.ExerciseSceneEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExerciseSceneMapper {
    
    /**
     * 插入演练场景
     */
    int insert(ExerciseSceneEntity scene);
    
    /**
     * 根据ID查询场景
     */
    ExerciseSceneEntity selectById(@Param("id") String id);
    
    /**
     * 查询所有场景
     */
    List<ExerciseSceneEntity> selectAll();
    
    /**
     * 根据状态查询场景
     */
    List<ExerciseSceneEntity> selectByStatus(@Param("status") String status);
    
    /**
     * 根据类型查询场景
     */
    List<ExerciseSceneEntity> selectByType(@Param("type") String type);
    
    /**
     * 更新场景信息
     */
    int update(ExerciseSceneEntity scene);
    
    /**
     * 根据ID删除场景
     */
    int deleteById(@Param("id") String id);
}
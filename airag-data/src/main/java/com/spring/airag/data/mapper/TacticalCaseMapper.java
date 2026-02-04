package com.spring.airag.data.mapper;

import com.spring.airag.data.entity.TacticalCaseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TacticalCaseMapper {
    
    /**
     * 插入战术案例
     */
    int insert(TacticalCaseEntity tacticalCase);
    
    /**
     * 根据ID查询战术案例
     */
    TacticalCaseEntity selectById(@Param("id") String id);
    
    /**
     * 查询所有战术案例
     */
    List<TacticalCaseEntity> selectAll();
    
    /**
     * 根据场景类型查询
     */
    List<TacticalCaseEntity> selectByScenarioType(@Param("scenarioType") String scenarioType);
    
    /**
     * 根据难度等级查询
     */
    List<TacticalCaseEntity> selectByDifficultyLevel(@Param("difficultyLevel") Integer difficultyLevel);
    
    /**
     * 更新战术案例
     */
    int update(TacticalCaseEntity tacticalCase);
    
    /**
     * 根据ID删除战术案例
     */
    int deleteById(@Param("id") String id);
}
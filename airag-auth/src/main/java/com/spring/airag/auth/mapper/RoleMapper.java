package com.spring.airag.auth.mapper;

import com.spring.airag.auth.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色Mapper接口
 */
@Mapper
public interface RoleMapper {
    /**
     * 根据ID查询角色
     */
    Role selectById(@Param("id") Integer id);

    /**
     * 查询所有角色
     */
    List<Role> selectAll();

    /**
     * 根据角色名称查询角色
     */
    Role selectByRoleName(@Param("roleName") String roleName);

    /**
     * 插入角色
     */
    int insert(Role role);

    /**
     * 更新角色
     */
    int update(Role role);

    /**
     * 删除角色
     */
    int deleteById(@Param("id") Integer id);
}
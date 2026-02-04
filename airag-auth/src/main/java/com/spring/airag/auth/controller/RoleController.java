package com.spring.airag.auth.controller;

import com.spring.airag.auth.entity.Role;
import com.spring.airag.auth.mapper.RoleMapper;
import com.spring.airag.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 */
@RestController
@RequestMapping("/api/auth/role")
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 获取所有角色
     */
    @GetMapping("/list")
    public Result<List<Role>> getAllRoles() {
        List<Role> roles = roleMapper.selectAll();
        return Result.success(roles);
    }

    /**
     * 根据ID获取角色
     */
    @GetMapping("/{id}")
    public Result<Role> getRoleById(@PathVariable Integer id) {
        Role role = roleMapper.selectById(id);
        if (role != null) {
            return Result.success(role);
        } else {
            return Result.error("角色不存在");
        }
    }

    /**
     * 创建角色
     */
    @PostMapping
    public Result<Role> createRole(@RequestBody Role role) {
        role.setCreatedAt(java.sql.Timestamp.from(java.time.Instant.now()));
        int result = roleMapper.insert(role);
        if (result > 0) {
            return Result.success(role);
        } else {
            return Result.error("创建角色失败");
        }
    }

    /**
     * 更新角色
     */
    @PutMapping("/{id}")
    public Result<Role> updateRole(@PathVariable Integer id, @RequestBody Role roleDetails) {
        Role existingRole = roleMapper.selectById(id);
        if (existingRole != null) {
            roleDetails.setId(id);
            int result = roleMapper.update(roleDetails);
            if (result > 0) {
                return Result.success(roleDetails);
            } else {
                return Result.error("更新角色失败");
            }
        } else {
            return Result.error("角色不存在");
        }
    }

    /**
     * 删除角色
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteRole(@PathVariable Integer id) {
        int result = roleMapper.deleteById(id);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.error("角色不存在");
        }
    }
}
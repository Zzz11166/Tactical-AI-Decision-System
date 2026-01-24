package com.spring.airag.auth.service;

import com.spring.airag.auth.entity.Role;
import com.spring.airag.auth.entity.User;
import com.spring.airag.auth.mapper.RoleMapper;
import com.spring.airag.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.util.Date;

/**
 * 用户初始化服务，用于初始化系统默认角色和用户
 */
@Service
public class UserInitializationService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeDefaultUsersAndRoles() {
        // 创建默认角色
        createDefaultRoles();

        // 创建默认用户
        createDefaultUsers();
    }

    private void createDefaultRoles() {
        // 创建ADMIN角色
        if (roleMapper.selectByRoleName("ADMIN") == null) {
            Role adminRole = new Role();
            adminRole.setRoleName("ADMIN");
            adminRole.setDescription("系统管理员角色");
            adminRole.setCreatedAt(new Date());
            roleMapper.insert(adminRole);
        }

        // 创建USER角色
        if (roleMapper.selectByRoleName("USER") == null) {
            Role userRole = new Role();
            userRole.setRoleName("USER");
            userRole.setDescription("普通用户角色");
            userRole.setCreatedAt(new Date());
            roleMapper.insert(userRole);
        }

        // 创建DIRECTOR角色
        if (roleMapper.selectByRoleName("DIRECTOR") == null) {
            Role directorRole = new Role();
            directorRole.setRoleName("DIRECTOR");
            directorRole.setDescription("导演角色");
            directorRole.setCreatedAt(new Date());
            roleMapper.insert(directorRole);
        }
    }

    private void createDefaultUsers() {
        // 检查是否存在admin用户
        if (userMapper.selectByUsername("admin") == null) {
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword(passwordEncoder.encode("admin123")); // 默认密码
            adminUser.setEmail("admin@example.com");
            adminUser.setRole("ADMIN");
            adminUser.setStatus(User.UserStatus.ACTIVE);
            adminUser.setCreatedAt(new Date());
            adminUser.setUpdatedAt(new Date());

            userMapper.insert(adminUser);
        }

        // 检查是否存在director用户
        if (userMapper.selectByUsername("director") == null) {
            User directorUser = new User();
            directorUser.setUsername("director");
            directorUser.setPassword(passwordEncoder.encode("director123")); // 默认密码
            directorUser.setEmail("director@example.com");
            directorUser.setRole("DIRECTOR");
            directorUser.setStatus(User.UserStatus.ACTIVE);
            directorUser.setCreatedAt(new Date());
            directorUser.setUpdatedAt(new Date());

            userMapper.insert(directorUser);
        }
    }
}
package com.spring.airag.auth.controller;

import com.spring.airag.auth.dto.LoginRequest;
import com.spring.airag.auth.dto.AuthResponse;
import com.spring.airag.auth.entity.User;
import com.spring.airag.auth.mapper.UserMapper;
import com.spring.airag.auth.service.UserService;
import com.spring.airag.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            AuthResponse response = userService.login(loginRequest);
            return Result.success(response);
        } catch (Exception e) {
            return Result.error("登录失败: " + e.getMessage());
        }
    }

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Result<User> register(@Valid @RequestBody User user) {
        try {
            User registeredUser = userService.register(user);
            return Result.success(registeredUser);
        } catch (Exception e) {
            return Result.error("注册失败: " + e.getMessage());
        }
    }

    /**
     * 检查用户是否已认证
     */
    @GetMapping("/check")
    public Result<String> checkAuth() {
        return Result.success("用户已认证");
    }

    /**
     * 获取所有用户
     */
    @GetMapping("/users")
    public Result<List<User>> getAllUsers() {
        List<User> users = userMapper.selectAll();
        return Result.success(users);
    }

    /**
     * 根据ID获取用户
     */
    @GetMapping("/user/{id}")
    public Result<User> getUserById(@PathVariable Integer id) {
        User user = userMapper.selectById(id);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("用户不存在");
        }
    }

    /**
     * 根据用户名获取用户
     */
    @GetMapping("/user/username/{username}")
    public Result<User> getUserByUsername(@PathVariable String username) {
        User user = userMapper.selectByUsername(username);
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.error("用户不存在");
        }
    }
}
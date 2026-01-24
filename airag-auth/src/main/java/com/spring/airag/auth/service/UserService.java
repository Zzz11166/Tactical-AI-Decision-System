package com.spring.airag.auth.service;

import com.spring.airag.auth.dto.LoginRequest;
import com.spring.airag.auth.dto.AuthResponse;
import com.spring.airag.auth.entity.User;

/**
 * 用户服务接口
 */
public interface UserService {
    /**
     * 用户登录
     */
    AuthResponse login(LoginRequest loginRequest);

    /**
     * 用户注册
     */
    User register(User user);

    /**
     * 根据用户名查找用户
     */
    User findByUsername(String username);

    /**
     * 刷新令牌
     */
    AuthResponse refreshToken(String refreshToken);
}
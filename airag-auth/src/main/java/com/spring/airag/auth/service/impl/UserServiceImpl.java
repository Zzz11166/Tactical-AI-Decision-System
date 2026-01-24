package com.spring.airag.auth.service.impl;

import com.spring.airag.auth.dto.LoginRequest;
import com.spring.airag.auth.dto.AuthResponse;
import com.spring.airag.auth.entity.User;
import com.spring.airag.auth.mapper.UserMapper;
import com.spring.airag.auth.service.UserService;
import com.spring.airag.auth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        // 使用Spring Security进行身份验证
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 获取用户信息
        User user = (User) authentication.getPrincipal();

        // 生成JWT令牌
        String roles = user.getRole();

        String jwtToken = jwtUtil.generateToken(user.getUsername(), roles);

        return new AuthResponse(
                jwtToken,
                null, // refresh token暂时为空
                "Bearer",
                jwtUtil.getExpirationDateFromToken(jwtToken).getTime(),
                user.getUsername()
        );
    }

    @Override
    public User register(User user) {
        // 检查用户名是否已存在
        if (userMapper.existsByUsername(user.getUsername()) > 0) {
            throw new RuntimeException("用户名已存在");
        }

        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 设置默认状态
        if (user.getStatus() == null) {
            user.setStatus(User.UserStatus.ACTIVE); // 激活状态
        }

        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER"); // 默认角色
        }

        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        // 插入用户
        userMapper.insert(user);

        return user;
    }

    @Override
    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {
        // 实现刷新令牌逻辑（这里简化处理）
        // 在实际应用中，需要验证refresh token的有效性
        throw new UnsupportedOperationException("刷新令牌功能暂未实现");
    }
}
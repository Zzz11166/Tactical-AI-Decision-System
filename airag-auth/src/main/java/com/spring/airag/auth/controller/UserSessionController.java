package com.spring.airag.auth.controller;

import com.spring.airag.auth.entity.UserSession;
import com.spring.airag.auth.mapper.UserSessionMapper;
import com.spring.airag.common.dto.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户会话控制器
 */
@RestController
@RequestMapping("/api/session")
public class UserSessionController {

    @Autowired
    private UserSessionMapper userSessionMapper;

    /**
     * 获取所有会话
     */
    @GetMapping("/list")
    public Result<List<UserSession>> getAllSessions() {
        List<UserSession> sessions = userSessionMapper.selectAll();
        return Result.success(sessions);
    }

    /**
     * 根据ID获取会话
     */
    @GetMapping("/{id}")
    public Result<UserSession> getSessionById(@PathVariable Integer id) {
        UserSession session = userSessionMapper.selectById(id);
        if (session != null) {
            return Result.success(session);
        } else {
            return Result.error("会话不存在");
        }
    }

    /**
     * 根据用户ID获取会话
     */
    @GetMapping("/user/{userId}")
    public Result<List<UserSession>> getSessionsByUserId(@PathVariable Integer userId) {
        List<UserSession> sessions = userSessionMapper.selectByUserId(userId);
        return Result.success(sessions);
    }

    /**
     * 根据会话令牌获取会话
     */
    @GetMapping("/token/{token}")
    public Result<UserSession> getSessionByToken(@PathVariable String token) {
        UserSession session = userSessionMapper.selectBySessionToken(token);
        if (session != null) {
            return Result.success(session);
        } else {
            return Result.error("会话不存在");
        }
    }

    /**
     * 删除会话
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteSession(@PathVariable Integer id) {
        int result = userSessionMapper.deleteById(id);
        if (result > 0) {
            return Result.success();
        } else {
            return Result.error("会话不存在");
        }
    }

    /**
     * 删除指定用户的会话
     */
    @DeleteMapping("/user/{userId}")
    public Result<Void> deleteSessionsByUserId(@PathVariable Integer userId) {
        int result = userSessionMapper.deleteByUserId(userId);
        if (result >= 0) {
            return Result.success();
        } else {
            return Result.error("删除会话失败");
        }
    }
}
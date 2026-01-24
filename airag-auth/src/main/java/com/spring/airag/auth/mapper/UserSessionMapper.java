package com.spring.airag.auth.mapper;

import com.spring.airag.auth.entity.UserSession;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户会话Mapper接口
 */
@Mapper
public interface UserSessionMapper {
    /**
     * 根据ID查询会话
     */
    UserSession selectById(@Param("id") Integer id);

    /**
     * 查询所有会话
     */
    List<UserSession> selectAll();

    /**
     * 根据会话令牌查询会话
     */
    UserSession selectBySessionToken(@Param("sessionToken") String sessionToken);

    /**
     * 根据用户ID查询会话
     */
    List<UserSession> selectByUserId(@Param("userId") Integer userId);

    /**
     * 插入会话
     */
    int insert(UserSession userSession);

    /**
     * 更新会话
     */
    int update(UserSession userSession);

    /**
     * 删除会话
     */
    int deleteById(@Param("id") Integer id);

    /**
     * 根据会话令牌删除会话
     */
    int deleteBySessionToken(@Param("sessionToken") String sessionToken);

    /**
     * 根据用户ID删除会话
     */
    int deleteByUserId(@Param("userId") Integer userId);
}
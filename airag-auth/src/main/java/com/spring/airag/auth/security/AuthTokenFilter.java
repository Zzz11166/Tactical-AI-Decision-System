package com.spring.airag.auth.security;

import com.spring.airag.auth.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT认证过滤器
 */
@Component
public class AuthTokenFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private CustomUserDetailsService userDetailsService;

/**
 * 内部方法：执行过滤器逻辑
 * @param request HttpServletRequest对象，包含客户端请求信息
 * @param response HttpServletResponse对象，用于生成响应
 * @param filterChain FilterChain对象，用于调用链中的下一个过滤器
 * @throws ServletException 可能抛出的Servlet异常
 * @throws IOException 可能抛出的IO异常
 */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // 从请求中解析JWT令牌
            String jwt = parseJwt(request);
            // 验证JWT令牌是否有效且不为空
            if (jwt != null && jwtUtil.validateToken(jwt)) {
                // 从JWT令牌中获取用户名
                String username = jwtUtil.getUsernameFromToken(jwt);

                // 通过用户名加载用户详细信息
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
                // 创建用户认证令牌
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                // 设置认证详情
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // 将认证信息设置到安全上下文中
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            // 记录认证设置失败的错误日志
            logger.error("Cannot set user authentication: {}", e);
        }

        // 将请求传递给过滤器链中的下一个资源
        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
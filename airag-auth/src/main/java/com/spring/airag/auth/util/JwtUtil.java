package com.spring.airag.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JWT工具类
 */
@Component
public class JwtUtil {

    @Value("${airag.jwt.secret}")
    private String secret;

    @Value("${airag.jwt.expiration}")
    private Long expiration;

    /**
     * 获取签名密钥
     * @return 密钥对象
     */
    private Key getSigningKey() {
        // 将配置的密钥字符串进行处理，确保符合HS512算法的安全要求
        if (secret == null || secret.isEmpty()) {
            // 如果没有配置密钥，使用一个安全的默认密钥
            return Keys.secretKeyFor(SignatureAlgorithm.HS512);
        } else {
            // 确保密钥长度符合HS512算法要求（至少512位/64字节）
            try {
                byte[] keyBytes = secret.getBytes();
                if (keyBytes.length < 64) {
                    // 如果密钥长度不足，使用SHA-512哈希处理以确保足够的长度和安全性
                    MessageDigest sha512Digest = MessageDigest.getInstance("SHA-512");
                    byte[] hashedKey = sha512Digest.digest(secret.getBytes());
                    return Keys.hmacShaKeyFor(hashedKey);
                } else {
                    return Keys.hmacShaKeyFor(keyBytes);
                }
            } catch (NoSuchAlgorithmException e) {
                // 如果SHA-512算法不可用，使用默认安全密钥
                return Keys.secretKeyFor(SignatureAlgorithm.HS512);
            }
        }
    }

    /**
     * 生成JWT令牌
     *
     * @param username 用户名
     * @param roles 用户角色列表
     * @return JWT令牌
     */
    public String generateToken(String username, String roles) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("roles", roles);
        claims.put("created", new Date());

        return generateToken(claims);
    }

    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(expirationDate)
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    /**
     * 验证JWT令牌
     *
     * @param token 令牌
     * @param username 用户名
     * @return 是否有效
     */
    public Boolean validateToken(String token, String username) {
        String tokenUsername = getUsernameFromToken(token);
        return tokenUsername != null && tokenUsername.equals(username) && !isTokenExpired(token);
    }

    /**
     * 验证JWT令牌（仅验证令牌有效性）
     *
     * @param token 令牌
     * @return 是否有效
     */
    public Boolean validateToken(String token) {
        try {
            String username = getUsernameFromToken(token);
            return username != null && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 从JWT令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        try {
            return getClaimFromToken(token, Claims::getSubject);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从JWT令牌中获取角色信息
     *
     * @param token 令牌
     * @return 角色信息
     */
    public String getRolesFromToken(String token) {
        return getClaimFromToken(token, claims -> (String) claims.get("roles"));
    }

    /**
     * 判断JWT令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date());
    }

    /**
     * 从JWT令牌中获取过期时间
     *
     * @param token 令牌
     * @return 过期时间
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * 从JWT令牌中获取特定声明
     *
     * @param token 令牌
     * @param claimsResolver 声明解析器
     * @param <T> 声明类型
     * @return 声明值
     */
    public <T> T getClaimFromToken(String token, java.util.function.Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * 从JWT令牌中获取所有声明
     *
     * @param token 令牌
     * @return 所有声明
     */
    private Claims getAllClaimsFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSigningKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("JWT验证失败: " + e.getMessage(), e);
        }
    }
}
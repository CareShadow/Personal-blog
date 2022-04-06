package com.blog.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang.StringUtils;

import java.time.Duration;
import java.util.Date;

/**
 * @ClassName JwtUtils
 * @Description TODO
 * @Author admin
 * @Date 2022/4/6 15:30
 * @Version 1.0
 **/
public class JwtUtils {
    // 密钥 secretKey
    private final static String secretKey = "careshadow";
    // 过期时间 //Duration.ofHours() 获得Duration多个标准时数的Duration 2小时。
    private final static Duration expiration = Duration.ofHours(2);
    /**
     * 生成JWT
     * @param userName 用户名
     * @return JWT
     */
    public static String generate(String userName){
        // 过期时间
        Date expiryDate = new Date(System.currentTimeMillis()+expiration.toMillis());
        return Jwts.builder()
                .setSubject(userName) // 将userName放进JWT
                .setIssuedAt(new Date()) // 签发时间
                .setExpiration(expiryDate) //过期时间
                .signWith(SignatureAlgorithm.HS512,secretKey) // 设置加密算法和密钥
                .compact();
    }
    /**
     * 解析JWT
     * @param token JWT字符串
     * @return 解析成功返回Claims对象，解析失败返回null
     */
    public static Claims parse(String token) {
        // 如果是空字符串直接返回null
        if (StringUtils.isEmpty(token)) {
            return null;
        }

        // 这个Claims对象包含了许多属性，比如签发时间、过期时间以及存放的数据等
        Claims claims = null;
        // 解析失败了会抛出异常，所以我们要捕捉一下。token过期、token非法都会导致解析失败
        try {
            claims = Jwts.parser()
                    .setSigningKey(secretKey) // 设置秘钥
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException e) {
            // 这里应该用日志输出，为了演示方便就直接打印了
            System.err.println("解析失败！");
        }
        return claims;
    }
}

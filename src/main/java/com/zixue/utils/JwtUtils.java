package com.zixue.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    private static final String signKey = "wuyue";
    private static final Long expire = 43200000L;

    //生成令牌
    public static String generateJwt(Map<String, Object> claims){
        return Jwts.builder()
                .setClaims(claims) // 设置payload
                .signWith(SignatureAlgorithm.HS256, signKey) // 设置签名算法和签名使用的密钥
                .setExpiration(new Date(System.currentTimeMillis() + expire))  // 设置过期时间
                .compact();
    }

    // 解析令牌
    public static Claims parseJwt(String jwt){
        return Jwts.parser()
                .setSigningKey(signKey)
                .parseClaimsJws(jwt)
                .getBody();
    }
}

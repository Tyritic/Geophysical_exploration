package com.example.project.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 生成jwt令牌
     * 加密算法：HS256
     * 私钥使用固定密钥
     * @param secret_key 私钥
     * @param ttlMillis 有效时间
     * @param claims 载荷,payload中存储的数据
     * @return 令牌
     */

    public static String createJWT(String secret_key, long ttlMillis, Map<String, Object> claims) {
        //设置签名算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //生成jwt的时间
        long nowMillis = System.currentTimeMillis();
        Date exp = new Date(nowMillis + ttlMillis);
        //设置jwt的body
        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)//设置私有声明
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(signatureAlgorithm, secret_key.getBytes(StandardCharsets.UTF_8))
                .setExpiration(exp);//设置过期时间
        return builder.compact();
    }

    /**
     * 解密jwt令牌
     * @param secret_key 私钥
     * @param token 加密后的令牌
     * @return 令牌中的数据
     */

    public static Claims parseJWT(String secret_key, String token) {
        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(secret_key.getBytes(StandardCharsets.UTF_8))
                //设置需要解析的jwt
                .parseClaimsJws(token).getBody();
        return claims;
    }


}

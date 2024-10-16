package com.example.project.utilsTest;

import com.example.project.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JwtUtilTest {

    @Test
    public void testCreateJWT() {
        String secretKey = "mySecretKey";
        long ttlMillis = 3600000; // 1 hour
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "testUser");

        String token = JwtUtil.createJWT(secretKey, ttlMillis, claims);
        Assert.assertNotNull(token);

        Claims parsedClaims = JwtUtil.parseJWT(secretKey, token);
        Assert.assertEquals("testUser", parsedClaims.get("username"));
    }
}
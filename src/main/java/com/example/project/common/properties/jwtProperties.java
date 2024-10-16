package com.example.project.common.properties;

import org.springframework.stereotype.Component;

@Component
public class jwtProperties {
    /**
     * 微信小程序用户生成jwt令牌相关配置
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;
}

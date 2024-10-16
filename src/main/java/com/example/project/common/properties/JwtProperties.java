package com.example.project.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component //控制反转，将该类交给spring容器管理
@Data //自动生成getter和setter方法
@ConfigurationProperties(prefix = "project.jwt") //自动装配，读取配置文件中以jwt开头的配置

public class JwtProperties {

    /**
     * 微信小程序用户生成jwt令牌相关配置
     */
    private String userSecretKey;
    private long userTtl;
    private String userTokenName;
}

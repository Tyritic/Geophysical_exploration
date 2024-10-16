package com.example.project.common.context;

import java.util.Map;

// 用于存储当前线程的一些信息
//
public class BaseContext {
    public static ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal<>();

    public static void setCurrentMap(Map<String,Object> claims) {
        threadLocal.set(claims);
    }

    public static Map<String,Object> getCurrentMap() {
        return threadLocal.get();
    }

    public static void removeCurrentMap() {
        threadLocal.remove();
    }
}

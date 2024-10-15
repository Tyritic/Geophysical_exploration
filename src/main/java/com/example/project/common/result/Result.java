package com.example.project.common.result;

import com.example.project.common.constant.ResultConstant;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 * 后端统一返回结果
 * @param <T>
 */
@Data
public class Result<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Integer code; //编码：1成功，0和其它数字为失败
    private String msg; //相关描述
    private T data; //数据

    //返回成功,无数据
    public static <T> Result<T> success() {
        Result<T> result = new Result<T>();
        result.code = 1;
        result.msg = ResultConstant.SUCCESS;
        result.data = null;
        return result;
    }

    //返回成功,有数据
    public static <T> Result<T> success(T object) {
        Result<T> result = new Result<T>();
        result.code = 1;
        result.msg = ResultConstant.SUCCESS;
        result.data = object;
        return result;
    }

    //返回失败
    public static <T> Result<T> error(String msg) {
        Result<T> result = new Result<T>();
        result.code = 0;
        result.msg = msg;
        result.data = null;
        return result;
    }
}

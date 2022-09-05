package com.cloud.zuul.vo;

import com.cloud.zuul.constants.StatusCode;

/**
 * @author 10450
 * @description 通用返回类
 * @date 2022/9/2 17:04
 */
public class Result {
    private Integer code;
    private String message;
    private Object object = new Object();

    public static Result fail(StatusCode statusCode){
        Result result = new Result();
        result.setCode(statusCode.getCode());
        result.setMessage(statusCode.getMessage());
        return result;
    }

    public static Result success(Object object){
        Result result = new Result();
        result.setCode(StatusCode.SUCCESS.getCode());
        result.setMessage(StatusCode.SUCCESS.getMessage());
        result.setObject(object);
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}

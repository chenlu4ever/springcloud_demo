package com.cloud.zuul.constants;

/**
 * @author 10450
 * @description 常用返回
 * @date 2022/9/2 17:06
 */
public enum StatusCode {
    SUCCESS(0,"操作成功"),
    ERRPR(-1,"操作失败"),
    AUTH_VALIDATE_FAILED(10020,"TOKEN校验失败"),

    TOKEN_FAILED(30000,"TOKEN鉴权失败"),
    TOKEN_IS_EMPTY(30010,"TOKEN为空"),
    TOKEN_EXPIRED(30020,"TOKEN过期"),
//    AUTH_VALIDATE_FAILED(30020,"认证失败")
            ;

    private StatusCode(Integer code,String message){
        this.code=code;
        this.message=message;
    }

    public Integer code;
    public String message;

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
}

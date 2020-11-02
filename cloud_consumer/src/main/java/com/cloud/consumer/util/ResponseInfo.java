package com.cloud.consumer.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "ResponseInfo",description = "响应类")
public class ResponseInfo<T> {
    @ApiModelProperty(value = "返回码")
    private String rtnCode;
    @ApiModelProperty(value = "返回信息")
    private String rtnMsg;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public String getRtnCode() {
        return rtnCode;
    }

    public void setRtnCode(String rtnCode) {
        this.rtnCode = rtnCode;
    }

    public String getRtnMsg() {
        return rtnMsg;
    }

    public void setRtnMsg(String rtnMsg) {
        this.rtnMsg = rtnMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

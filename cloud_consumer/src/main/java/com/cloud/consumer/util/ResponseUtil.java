package com.cloud.consumer.util;

public class ResponseUtil {
    public static final String SUCCESS_CODE = "000";
    public static final String SUCCESS_MSG = "成功";

    public static final String ERROR_CODE = "999";
    public static final String ERROR_MSG = "失败";

    public static ResponseInfo success(Object object){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setRtnCode(SUCCESS_CODE);
        responseInfo.setRtnMsg(SUCCESS_MSG);
        responseInfo.setData(object);
        return responseInfo;
    }

    public static ResponseInfo error(){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setRtnCode(ERROR_CODE);
        responseInfo.setRtnMsg(ERROR_MSG);
        return responseInfo;
    }

    public static ResponseInfo error(String rtnCode,String rtnMsg, Object object){
        ResponseInfo responseInfo = new ResponseInfo();
        responseInfo.setRtnCode(rtnCode);
        responseInfo.setRtnMsg(rtnMsg);
        responseInfo.setData(object);
        return responseInfo;
    }

}

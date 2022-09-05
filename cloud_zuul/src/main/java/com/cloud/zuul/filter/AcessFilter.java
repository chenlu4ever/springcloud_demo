package com.cloud.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.cloud.zuul.constants.StatusCode;
import com.cloud.zuul.service.AuthService;
import com.cloud.zuul.vo.Result;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author 10450
 * @description 权限token校验
 * @date 2022/9/2 14:45
 */
@Component
public class AcessFilter extends ZuulFilter {
    private  static final Logger logger = LoggerFactory.getLogger(AcessFilter.class);

    @Autowired
    private AuthService authService;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER - 2;//最前进行校验
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        //1、获取request对象
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        String url = request.getRequestURI();
        logger.info("url:"+url);

        //2、获取token参数
        String token = request.getParameter("acessToken");
        if(StringUtils.isEmpty(token)){
            token = request.getHeader("acessToken");
        }

        logger.info("acessToken:"+token);

        //3、校验
        Result result =  authService.checkTokenByJWT(url,token);
        if(result !=null && result.getCode()!=StatusCode.SUCCESS.getCode()){

        }else{
            //4、token校验失败，直接响应数据
            Result ret =  new Result();
            ret.setCode(StatusCode.AUTH_VALIDATE_FAILED.getCode());
            ret.setMessage(StatusCode.AUTH_VALIDATE_FAILED.getMessage());
            ret.setObject(result);
            this.authFailedResponse(url,request,response,ret);
            return false;
        }
        return true;
    }

    private void authFailedResponse(String url, HttpServletRequest request, HttpServletResponse response,Result result) {
        logger.error("auth invoke"+url+" authValidate failed:"+ JSONObject.toJSONString(result));
        try {
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.append(JSONObject.toJSONString(result));
            out.close();
        } catch (IOException e) {
            logger.error("auth getWriter error {}",e);
        }
    }


}

package com.cloud.zuul.filter;

import com.alibaba.fastjson.JSONObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 10450
 * @description SQL注入
 * @date 2022/9/1 15:25
 */
@Component
public class SqlInjectionFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    //数值月大越靠后，越小越先
    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER -1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        try {
            ServletInputStream inputStream = request.getInputStream();
            String body = StreamUtils.copyToString(inputStream, Charset.forName("UTF-8"));
            if(!checkSql(body)){
                Map<String,Object> result = new HashMap<>();
                result.put("code","501");
                result.put("msg","存在SQL注入风险");
                ctx.getResponse().setContentType("text/html;charset=UTF-8");
                ctx.setResponseBody(new JSONObject(result).toJSONString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkSql(String body) {
        //TODO 校验逻辑
        return true;
    }
}

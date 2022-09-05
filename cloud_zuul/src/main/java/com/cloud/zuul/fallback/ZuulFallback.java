package com.cloud.zuul.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author 10450
 * @description ZUUL实现降级
 * @date 2022/9/2 14:36
 */
@Component
public class ZuulFallback implements FallbackProvider {
    @Override
    public String getRoute() {
        return "*"; //指定全部出现问题的服务都走这个降级方法
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {//route表示服务名
        System.out.println("降级的服务："+route);
        cause.printStackTrace();//问题打印
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return HttpStatus.INTERNAL_SERVER_ERROR.value(); //500状态码
            }

            @Override
            public String getStatusText() throws IOException {
                //指定错误信息
                return HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
            }

            @Override
            public void close() {

            }

            @Override
            public InputStream getBody() throws IOException {
                String msg = "当前服务:"+ route + "，出现问题";
                return new ByteArrayInputStream(msg.getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                //指定响应头信息
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }
}

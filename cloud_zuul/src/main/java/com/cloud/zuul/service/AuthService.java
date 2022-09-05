package com.cloud.zuul.service;

import com.cloud.zuul.vo.Result;
import org.springframework.stereotype.Service;

/**
 * @author 10450
 * @description 权限校验接口
 * @date 2022/9/2 17:31
 */
@Service
public interface AuthService {
    /**
     * @param url 请求URL
     * @param token token
     * @return
     */
    public Result checkTokenByJWT(String url, String token);
}

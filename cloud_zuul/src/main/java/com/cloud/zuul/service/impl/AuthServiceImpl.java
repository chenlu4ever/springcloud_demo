package com.cloud.zuul.service.impl;

import com.cloud.zuul.constants.StatusCode;
import com.cloud.zuul.service.AuthService;
import com.cloud.zuul.utils.JwtUtils;
import com.cloud.zuul.vo.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * @author 10450
 * @description 权限校验实现类
 * @date 2022/9/2 17:32
 */
public class AuthServiceImpl implements AuthService {
    private  static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    /**
     * JWT 校验
     * @param url
     * @param token
     * @return
     */
    @Override
    public Result checkTokenByJWT(String url, String token) {
        Result result = Result.success(null);
        if(StringUtils.isEmpty(token)){
            logger.error("auth token is empty~");
            return Result.fail(StatusCode.TOKEN_IS_EMPTY);
        }

        Jws<Claims> claims=null;
        try{
            claims = JwtUtils.parseJwtResultJws(token);
        }catch (ExpiredJwtException e1){
            return Result.fail(StatusCode.TOKEN_EXPIRED);
        }catch (Exception e){
            return Result.fail(StatusCode.TOKEN_FAILED);
        }

        //TODO 可以加一些业务校验
        return result;
    }
}

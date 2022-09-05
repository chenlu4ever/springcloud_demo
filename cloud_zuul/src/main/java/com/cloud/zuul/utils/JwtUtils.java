package com.cloud.zuul.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 10450
 * @description JWT工具类
 * @date 2022/9/2 17:58
 */
public class JwtUtils {

    //key长度大于等于256bit,一个英文字符为：8bit，至少大于32位（中文12bit）
    private static final String KEY="poiuytrewqasdfghjklmnbvcxzzbcdef";

    //设置加密算法
    private SignatureAlgorithm signatureAlgorithm= SignatureAlgorithm.HS256;
    /**
     * 获取转换后的私钥对象
     * @return
     */
    public static SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(KEY.getBytes());
    }

    /**
     * 生成JWT
     * @param exp 指定过期时间，不能小于当前时间
     * @param payLoad 携带的数据
     * @return
     */
    public String createJwt(Date exp , Map<String,Object> payLoad){
        return Jwts.builder()
                .setClaims(payLoad)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(exp)
                .signWith(getSecretKey(),signatureAlgorithm)
                .compact();
    }

    /**
     * 解析Jws,返回一个Jws对象
     * @param jwsString
     * @return
     */
    public static Jws parseJwtResultJws(String jwsString){
        Jws<Claims> claims=Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(jwsString);
        return claims;
    }
    /**
     * 获取PayLoad中携带的数据
     * @param jwsString
     * @return
     */
    public Map<String,Object> getPayLoad(String jwsString){
        return ((Map<String, Object>) (parseJwtResultJws(jwsString)).getBody());
    }

    /**
     * 获取除去exp和iat的数据，exp：过期时间，iat：JWT生成的时间
     * @param jwsString
     * @return
     */
    public Map<String,Object> getPayLoadALSOExcludeExpAndIat(String jwsString){
        Map<String, Object> map = getPayLoad(jwsString);
        map.remove("exp");
        map.remove("iat");
        return map;
    }

    public static void main(String[] args) {
        JwtUtils jwtUtils = new JwtUtils();
        //设置超时时间
        Date exp = new Date(System.currentTimeMillis()+60*60*1);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("USER_ID","CHENLU111");
        hashMap.put("USER_NAME","CHENLU111");
        String jwt = jwtUtils.createJwt(exp, hashMap);
        System.out.println(jwt);
        //MalformedJwtException  ExpiredJwtException
//        System.out.println(jwtUtils.getPayLoadALSOExcludeExpAndIat("eyJhbGciOiJIUzI1NiJ9.eyJVU0VSX0lEIjoiQ0hFTkxVMTExIiwiVVNFUl9OQU1FIjoiQ0hFTkxVMTExIiwiaWF0IjoxNjYyMTE0NTYxLCJleHAiOjE2NjIxMTQ2N7d9.5GNBc4qX8j1GTc_aooj8jzXBXG1Y3KEL3d7c6EvkCV4"));
        System.out.println(jwtUtils.getPayLoadALSOExcludeExpAndIat(jwt));
    }

}

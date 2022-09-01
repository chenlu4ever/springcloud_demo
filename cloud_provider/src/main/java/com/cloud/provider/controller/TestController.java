package com.cloud.provider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试接口
 */
@RestController
public class TestController {
    @RequestMapping(value = "/sayHi" ,method = RequestMethod.GET)
    public String sayHi(){
        return "你好！";
    }
}

package com.cloud.consumer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 10450
 * @description 测试超时
 * @date 2022/9/2 15:11
 */
@RestController
public class SleepController {
    @RequestMapping(value = "/sleep",method = RequestMethod.GET)
    public String sleep(){
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "睡醒了";
    }
}

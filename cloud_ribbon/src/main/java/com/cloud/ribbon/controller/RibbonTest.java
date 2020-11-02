package com.cloud.ribbon.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonTest {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/ribbonTest")
    public JSONObject ribbonTest(@RequestParam String tel) {
        System.out.println("tel = " + tel);
        return this.restTemplate.getForObject("http://cloud-provider/customer/queryCustomerInfoByTel?tel=2552", JSONObject.class);
    }
}

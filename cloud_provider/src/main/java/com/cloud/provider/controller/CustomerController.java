package com.cloud.provider.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
    @RequestMapping(value = "/queryCustomerInfoByTel",method = RequestMethod.GET)
    public JSONObject queryCustomerInfoByTel(@RequestParam("tel") String tel){
        System.out.println("tel = " + tel);
        JSONObject object = new JSONObject();
        object.put("111111","33333333");
        return object;
    }
}

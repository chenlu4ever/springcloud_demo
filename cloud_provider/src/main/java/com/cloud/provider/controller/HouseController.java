package com.cloud.provider.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/house")
public class HouseController {

    @RequestMapping(value = "/queryHouseList",method = RequestMethod.POST)
    public JSONObject queryHouseList(@RequestBody Map<String, Object> requestMap){
        System.out.println("requestMap = " + JSONObject.toJSONString(requestMap));
        JSONObject object = new JSONObject();
        object.put("444444","666666");
        return object;
    }
}

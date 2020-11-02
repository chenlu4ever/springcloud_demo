package com.cloud.consumer.feign;

import com.alibaba.fastjson.JSONObject;
import com.cloud.consumer.factory.HouseManegeFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name = "cloud-provider", fallbackFactory = HouseManegeFallbackFactory.class)
public interface ProviderClient {
    @RequestMapping(value = "/customer/queryCustomerInfoByTel",method = RequestMethod.GET)
    public JSONObject queryCustomerInfoByTel(@RequestParam("tel") String tel);

    @RequestMapping(value = "/house/queryHouseList",method = RequestMethod.POST)
    public JSONObject queryHouseList(@RequestBody Map<String, Object> requestMap);
}

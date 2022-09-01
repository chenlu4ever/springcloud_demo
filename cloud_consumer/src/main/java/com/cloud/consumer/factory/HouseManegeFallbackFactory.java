package com.cloud.consumer.factory;

import com.alibaba.fastjson.JSONObject;
import com.cloud.consumer.feign.ProviderClient;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class HouseManegeFallbackFactory implements FallbackFactory<ProviderClient> {
    public ProviderClient create(Throwable cause) {
        return new ProviderClient() {
            @Override
            public JSONObject queryCustomerInfoByTel(String tel) {
                JSONObject object = new JSONObject();
                object.put("data","I'm Hystrix from queryCustomerInfoByTel");
                return object;
            }

            @Override
            public JSONObject queryHouseList(Map<String, Object> requestMap) {
                JSONObject object = new JSONObject();
                object.put("data","I'm Hystrix from queryHouseList");
                return object;
            }

        };
    }
}

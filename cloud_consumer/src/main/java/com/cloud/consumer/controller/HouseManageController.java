package com.cloud.consumer.controller;

import com.cloud.consumer.feign.ProviderClient;
import com.cloud.consumer.util.ResponseInfo;
import com.cloud.consumer.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 房屋管理类
 */
@RestController
@RequestMapping(value = "/houseManage")
public class HouseManageController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProviderClient providerClient;

    /**
     * 根据电话号码查询客户信息
     * @param tel
     * @return
     */
    @RequestMapping(value = "/queryCustomerInfoByTel", method = RequestMethod.GET)
    public ResponseInfo queryCustomerInfoByTel(@RequestParam String tel){
        try{
            if(StringUtils.isEmpty(tel)){
                //TODO
            }
            return ResponseUtil.success(providerClient.queryCustomerInfoByTel(tel));
        }catch (Exception e){
            logger.info("queryCustomerInfoByTel error",e);
            return ResponseUtil.error();
        }
    }

    /**
     * 查询房源信息
     * @param requestMap
     * @return
     */
    @RequestMapping(value = "/queryHouseList", method = RequestMethod.POST)
    public ResponseInfo queryHouseList(@RequestBody Map<String,Object> requestMap){
        try{
            return ResponseUtil.success(providerClient.queryHouseList(requestMap));
        }catch (Exception e){
            logger.info("queryHouseList error",e);
            return ResponseUtil.error();
        }
    }


}

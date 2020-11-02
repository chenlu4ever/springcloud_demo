//package com.cloud.consumer.controller;
//
//
//import com.cloud.consumer.feign.UserFeignClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class UserController {
//    @Autowired
//    UserFeignClient userFeignClient;
//
//    @RequestMapping(value = "/consumerHello",method = RequestMethod.GET)
//    public String consumerTest(){
//        return userFeignClient.sayHi();
//    }
//}

package com.web;

import com.model.Boss;
import com.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class MainController {


    @Autowired
    private Boss boss;

    @Resource(name = "redisTemplate")
    private SetOperations<String, Integer> setOperations;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, Integer> valueOperations;

    @RequestMapping("/")
    @ResponseBody
    Car getCar() {
        //Object o = sOps.pop("123");
        return boss.getCar();
    }
}
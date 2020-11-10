package com.jango.demo.controller;

import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 自定义配置文件使用demo
 */
@RestController
@RequestMapping("/jango/abc/")
@PropertySource("classpath:xfx.properties")
public class CustomizeController {

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postArea(){
        return "";
    }

}

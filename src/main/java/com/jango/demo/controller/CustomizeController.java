package com.jango.demo.controller;

import com.alibaba.fastjson.JSON;
import com.jango.demo.domain.XfxDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 自定义配置文件使用demo
 */
@RestController
@RequestMapping("/jango/")
@PropertySource("classpath:xfx.properties")
public class CustomizeController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomizeController.class);
    @Resource
    XfxDto xfx;
    @Value("${xfx.name:jango}")
    private String name;

    @RequestMapping(value = "/getConfigXfxDto", method = RequestMethod.POST)
    public XfxDto postArea() {
        LOGGER.info("xfx dto:{},name:{}", JSON.toJSONString(xfx), name);
        return xfx;
    }

}

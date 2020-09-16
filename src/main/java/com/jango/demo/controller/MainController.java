package com.jango.demo.controller;

import com.jango.demo.domain.dto.SmArea;
import com.jango.demo.domain.dto.SmAreaExample;
import com.jango.demo.domain.BaseResp;
import com.jango.demo.mapper.SmAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiongfeixiang
 * @title MainController
 * @date 2019/1/31 下午3:30
 * @since v1.0.0
 */
@RestController
@RequestMapping("/jango/")
public class MainController {
    // swagger2
    // logback
    // jwt
    // guava eventbus cache


    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Resource
    SmAreaMapper smAreaMapper;

    @RequestMapping("index")
    public BaseResp index() {
        LOGGER.info("获取区域信息测试:{}", "boot");
        SmAreaExample example = new SmAreaExample();
        example.createCriteria().andAreaNameEqualTo("双流区");
        List<SmArea> list = smAreaMapper.selectByExample(example);
        list.forEach(v -> System.out.println(v.getAreaName()));
        return new BaseResp(list);
    }

    @RequestMapping("add")
    @Transactional
    public String add() {
        LOGGER.info("获取区域信息测试add:{}", "add");
        SmArea example = new SmArea();
        example.setAreaId("1");
        example.setAreaName("华东区");
        example.setUpperAreaId("9999");
        int result = smAreaMapper.insertSelective(example);
        return "j add..." + result;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public void postArea(){

    }

}

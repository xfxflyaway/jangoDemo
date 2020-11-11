package com.jango.demo.controller;

import com.alibaba.fastjson.JSON;
import com.jango.demo.domain.BaseResp;
import com.jango.demo.domain.XfxDto;
import com.jango.demo.domain.dto.SmArea;
import com.jango.demo.domain.dto.SmAreaExample;
import com.jango.demo.mapper.SmAreaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author xiongfeixiang
 * @title MainController
 * @date 2019/1/31 下午3:30
 * @since v1.0.0
 */
@RestController
@RequestMapping("/jango")
public class MainController {
    // swagger2
    // logback
    // jwt
    // guava eventbus cache

    @Resource
    XfxDto xfx;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Resource
    SmAreaMapper smAreaMapper;

    @RequestMapping("/index")
    public BaseResp index() {
        LOGGER.info("获取区域信息测试:{}", "boot");
        SmAreaExample example = new SmAreaExample();
        example.createCriteria().andAreaNameEqualTo("普陀区");
        List<SmArea> list = smAreaMapper.selectByExample(example);
        list.forEach(v -> System.out.println(v.getAreaName()));
        System.out.println(JSON.toJSONString(xfx));
        return new BaseResp(list);
    }

    @RequestMapping("/area/add")
    @Transactional
    public String add(@RequestBody SmArea area) {
        LOGGER.info("获取区域信息测试area:{}", JSON.toJSONString(area));
        int result = smAreaMapper.insertSelective(area);
        return "j add..." + result;
    }

    @RequestMapping(value = "/views/jsp")
    public ModelAndView jsp() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("name", xfx.getName());
        return mv;
    }

}
